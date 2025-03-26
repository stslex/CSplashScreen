package st.slex.csplashscreen.feature.home.ui.presenter

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.home.domain.HomeInteractor
import st.slex.csplashscreen.feature.home.navigation.HomeRouter
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.Action
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.Event
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.Navigation
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.State

class HomeStore(
    private val interactor: HomeInteractor,
    appDispatcher: AppDispatcher,
    router: HomeRouter
) : Store<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INIT
) {

    private val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) { PagingSource(interactor::getAllCollections) }
            .state { collection -> collection.toPresentation() }

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllPhotos)
        }
            .state { image -> image.toPresentation() }

    override fun sendAction(action: Action) {
        super.sendAction(action)
        when (action) {
            is Action.Init -> actionInit()
            is Action.OnCollectionClick -> actionCollectionClick(action)
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
            is Action.CollectionsPagingState -> actionCollectionPagingState(action)
            is Action.PhotosPagingState -> actionPhotosPagingState(action)
        }
    }

    private fun actionInit() {
        collections.launch { data ->
            updateState { currentState ->
                currentState.copy(collections = data)
            }
        }
        photos.launch { data ->
            updateState { currentState ->
                currentState.copy(photos = data)
            }
        }
    }

    private fun actionCollectionClick(action: Action.OnCollectionClick) {
        navigate(Navigation.Collection(action.uuid))
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        navigate(Navigation.Image(action.uuid))
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        navigate(Navigation.User(action.username))
    }

    private fun actionCollectionPagingState(action: Action.CollectionsPagingState) {
        action.combinedLoadStates.checkError()?.let { e(it) }
    }

    private fun actionPhotosPagingState(action: Action.PhotosPagingState) {
        action.combinedLoadStates.checkError()?.let { e(it) }
    }

    private fun CombinedLoadStates.checkError(): Throwable? {
        if (refresh is LoadState.Error) {
            return (refresh as LoadState.Error).error
        }
        if (append is LoadState.Error) {
            return (append as LoadState.Error).error
        }
        if (prepend is LoadState.Error) {
            return (prepend as LoadState.Error).error
        }
        return null
    }

    companion object {

        private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}