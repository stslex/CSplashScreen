package st.slex.csplashscreen.feature.user.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.navigation.UserRouter
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Event
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Navigation
import st.slex.csplashscreen.feature.user.ui.store.UserStore.State
import javax.inject.Inject

class UserStoreImpl @Inject constructor(
    private val interactor: UserInteractor,
    router: UserRouter
) : UserStore, BaseStore<State, Event, Action, Navigation>(router) {

    override val initialState: State = State(
        user = null,
        likes = ::getLikes,
        photos = ::getPhotos,
        collections = ::getCollections
    )

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
        interactor
            .getUser(action.args.username)
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
    }

    private fun getPhotos(
        username: String
    ): StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserPhotos(
                username = username,
                page = page,
                pageSize = pageSize
            ).map { it.toPresentation() }
        }
    }.flow.state()

    private fun getLikes(
        username: String
    ): StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserLikePhotos(
                username = username,
                page = page,
                pageSize = pageSize
            ).map { it.toPresentation() }
        }
    }.flow.state()

    private fun getCollections(
        username: String
    ): StateFlow<PagingData<CollectionModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getUserCollections(
                username = username,
                page = page,
                pageSize = pageSize
            ).map { it.toPresentation() }
        }
    }.flow.state()

    private fun actionBackClick() {
        navigate(Navigation.PopBack)
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

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}