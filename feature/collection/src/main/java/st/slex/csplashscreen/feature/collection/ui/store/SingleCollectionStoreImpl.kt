package st.slex.csplashscreen.feature.collection.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.State
import javax.inject.Inject

class SingleCollectionStoreImpl @Inject constructor(
    private val interactor: SingleCollectionInteractor
) : SingleCollectionStore, BaseStoreImpl<State, Event, Action>() {


    override val initialState: State
        get() = State(
            photos = { MutableStateFlow(PagingData.empty()) }
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
                photos = {
                    getPhotos(action.collectionId)
                }
            )
        }
    }

    private fun getPhotos(collectionId: String) = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getPhotos(
                uuid = collectionId,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .flow
        .map { pagingData -> pagingData.map { it.toPresentation() } }
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)
        .stateIn(
            initialValue = PagingData.empty(),
            scope = scope,
            started = SharingStarted.Lazily
        )

    private fun actionProfileClick(action: Action.OnProfileClick) {
        sendEvent(Event.Navigation.Profile(action.username))
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        sendEvent(Event.Navigation.ImageDetail(action.uuid))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        )
    }
}