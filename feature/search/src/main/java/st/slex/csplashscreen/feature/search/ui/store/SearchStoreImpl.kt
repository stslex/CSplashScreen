package st.slex.csplashscreen.feature.search.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.ui.model.SearchItem
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Action
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Event
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.State
import javax.inject.Inject

class SearchStoreImpl @Inject constructor(
    val interactor: SearchPhotosInteractor
) : SearchStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState = State(
        query = "",
        searchItems = ::photosSearch,
        historyItems = ::searchHistory
    )

    override val state = MutableStateFlow(initialState)

    private val searchHistory: StateFlow<PagingData<SearchItem>>
        get() = interactor.searchHistory
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val photosSearch: StateFlow<PagingData<PhotoModel>>
        get() = state.map { it.query }
            .distinctUntilChanged()
            .map(::newPagerPhotosSearch)
            .flatMapLatest { it.flow }
            .map { pagingData -> pagingData.map { it.toPresentation() } }
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

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
        sendEvent(Event.Navigation.Profile(action.username))
    }

    private fun actionOnImageClick(action: Action.OnImageClick) {
        sendEvent(Event.Navigation.ImageDetail(action.uuid))
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