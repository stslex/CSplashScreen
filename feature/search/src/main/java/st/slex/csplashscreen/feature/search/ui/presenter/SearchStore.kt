package st.slex.csplashscreen.feature.search.ui.presenter

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouter
import st.slex.csplashscreen.feature.search.ui.model.SearchItem
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.Action
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.Event
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.Navigation
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.State

class SearchStore(
    private val interactor: SearchPhotosInteractor,
    appDispatcher: AppDispatcher,
    router: SearchPhotosRouter
) : Store<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {

    private val searchHistory: StateFlow<PagingData<SearchItem>>
        get() = interactor.searchHistory.state()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val photosSearch: StateFlow<PagingData<PhotoModel>>
        get() = state.map { it.query }
            .distinctUntilChanged()
            .map(::newPagerPhotosSearch)
            .flatMapLatest { it.flow }
            .map { pagingData -> pagingData.map { it.toPresentation() } }
            .state()

    override fun sendAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.ClearHistory -> actionClearHistory()
            is Action.OnImageClick -> actionOnImageClick(action)
            is Action.OnProfileClick -> actionOnProfileClick(action)
            is Action.OnQueryInput -> actionQueryInput(action)
            is Action.OnSearchHistoryClick -> actionSearchHistoryClick(action)
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(query = action.screen.query.trimEnd())
        }
        searchHistory.launch { data ->
            updateState { currentState ->
                currentState.copy(historyItems = data)
            }
        }
        photosSearch.launch { data ->
            updateState { currentState ->
                currentState.copy(searchItems = data)
            }
        }
    }

    private fun newPagerPhotosSearch(
        query: String
    ): Pager<Int, ImageModel> = Pager(config) {
        PagingSource { page, pageSize ->
            if (query.isBlank()) {
                emptyList()
            } else {
                interactor.getPhotos(query, page, pageSize)
            }
        }
    }

    private fun actionSearchHistoryClick(action: Action.OnSearchHistoryClick) {
        updateState { currentState ->
            currentState.copy(query = action.query)
        }
    }

    private fun actionQueryInput(action: Action.OnQueryInput) {
        updateState { currentState ->
            currentState.copy(query = action.query)
        }
    }

    private fun actionOnProfileClick(action: Action.OnProfileClick) {
        navigate(Navigation.Profile(action.username))
    }

    private fun actionOnImageClick(action: Action.OnImageClick) {
        navigate(Navigation.ImageDetail(action.uuid))
    }

    private fun actionClearHistory() {
        launch {
            runCatching {
                interactor.clearHistory()
            }.onFailure { error ->
                Logger.e(error)
            }
        }
    }

    companion object {
        private val config = PagingConfig(
            pageSize = 3,
            enablePlaceholders = false
        )
    }
}