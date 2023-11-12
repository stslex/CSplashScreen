package st.slex.csplashscreen.feature.collection.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouter
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Navigation
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.State
import javax.inject.Inject

class SingleCollectionStoreImpl @Inject constructor(
    private val interactor: SingleCollectionInteractor,
    appDispatcher: AppDispatcher,
    router: SingleCollectionRouter
) : SingleCollectionStore, BaseStore<State, Event, Action, Navigation>(router, appDispatcher) {

    override val initialState: State
        get() = State(
            photos = ::allPhotos,
            collectionId = ""
        )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.OnProfileClick -> actionProfileClick(action)
            is Action.OnImageClick -> actionImageClick(action)
        }
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(
                collectionId = action.collectionId
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val allPhotos: StateFlow<PagingData<PhotoModel>>
        get() = state
            .mapState { currentState ->
                currentState.collectionId
            }
            .filter { it.isNotBlank() }
            .flatMapLatest { collectionId ->
                Pager(pagingConfig) {
                    PagingSource { page, pageSize ->
                        interactor.getPhotos(
                            uuid = collectionId,
                            page = page,
                            pageSize = pageSize
                        )
                    }
                }.flow
            }
            .map { pagingData -> pagingData.map { it.toPresentation() } }
            .state()

    private fun actionProfileClick(action: Action.OnProfileClick) {
        navigate(Navigation.Profile(action.username))
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        navigate(Navigation.ImageDetail(action.uuid))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        )
    }
}