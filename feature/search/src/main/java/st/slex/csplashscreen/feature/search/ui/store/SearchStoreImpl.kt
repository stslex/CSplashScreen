package st.slex.csplashscreen.feature.search.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouter
import st.slex.csplashscreen.feature.search.ui.model.SearchItem
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Action
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Event
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Navigation
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.State
import javax.inject.Inject

class SearchStoreImpl @Inject constructor(
    private val interactor: SearchPhotosInteractor,
    router: SearchPhotosRouter
) : SearchStore, BaseStore<State, Event, Action, Navigation>(router) {

    override val initialState = State(
        query = "",
        searchItems = ::photosSearch,
        historyItems = ::searchHistory
    )

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

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.ClearHistory -> actionClearHistory()
            is Action.OnImageClick -> actionOnImageClick(action)
            is Action.OnProfileClick -> actionOnProfileClick(action)
            is Action.OnQueryInput -> actionQueryInput(action)
        }
    }

    private fun actionQueryInput(action: Action.OnQueryInput) {
        if (state.value.query == action.query) return
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
        scope.launch(Dispatchers.IO) {
            runCatching {
                interactor.clearHistory()
            }.onFailure { error ->
                Logger.exception(error)
            }
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(query = action.args.checkedQuery)
        }
    }

    companion object {
        private val config = PagingConfig(
            pageSize = 3,
            enablePlaceholders = false
        )
    }
}