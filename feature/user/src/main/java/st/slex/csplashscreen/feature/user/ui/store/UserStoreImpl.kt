package st.slex.csplashscreen.feature.user.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Event
import st.slex.csplashscreen.feature.user.ui.store.UserStore.State
import javax.inject.Inject

class UserStoreImpl @Inject constructor(
    private val interactor: UserInteractor
) : UserStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        user = null,
        likes = { MutableStateFlow(PagingData.empty()) },
        photos = { MutableStateFlow(PagingData.empty()) },
        collections = { MutableStateFlow(PagingData.empty()) }
    )

    override val state = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.OnBackButtonClick -> actionBackClick()
            is Action.OnCollectionClick -> actionCollectionClick(action)
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
        }
    }

    private fun actionInit(
        action: Action.Init
    ) {
        scope.cancel()
        val username = action.args.username
        interactor.getUser(username)
            .flowOn(Dispatchers.IO)
            .catch { error ->
                Logger.exception(error)
            }
            .onEach { user ->
                updateState { currentState ->
                    currentState.copy(user = user)
                }
            }
            .launchIn(scope)

        updateState { currentState ->
            currentState.copy(
                photos = { getPhotos(username) },
                likes = { getLikes(username) },
                collections = { getCollections(username) }
            )
        }
    }

    private fun getPhotos(
        username: String
    ): StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserPhotos(
                username = username,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .flow
        .map { pagingData -> pagingData.map { image -> image.toPresentation() } }
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)
        .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

    private fun getLikes(
        username: String
    ): StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserLikePhotos(
                username = username,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .flow
        .map { pagingData -> pagingData.map { image -> image.toPresentation() } }
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)
        .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

    private fun getCollections(
        username: String
    ): StateFlow<PagingData<CollectionModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserCollections(
                username = username,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .flow
        .map { pagingData -> pagingData.map { collection -> collection.toPresentation() } }
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)
        .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

    private fun actionBackClick() {
        sendEvent(Event.Navigation.PopBack)
    }

    private fun actionCollectionClick(action: Action.OnCollectionClick) {
        sendEvent(Event.Navigation.Collection(action.uuid))
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        sendEvent(Event.Navigation.Image(action.uuid))
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        sendEvent(Event.Navigation.User(action.username))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}