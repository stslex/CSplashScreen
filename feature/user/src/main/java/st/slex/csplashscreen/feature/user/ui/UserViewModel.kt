package st.slex.csplashscreen.feature.user.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.network.model.ui.user.UserModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.user.domain.UserInteractor

class UserViewModel(
    private val interactor: UserInteractor,
    private val navigator: Navigator,
    args: AppArguments.UserScreen,
) : BaseViewModel() {

    val username: String = args.username

    val user: StateFlow<UserModel?>
        get() = interactor
            .getUser(username)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = null
            )

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(pagingConfig) {
            PagingSource { page, pageSize ->
                interactor.getUserPhotos(
                    username = username,
                    page = page,
                    pageSize = pageSize
                )
            }
        }.mapState { image -> image.toPresentation() }

    val likes: StateFlow<PagingData<PhotoModel>>
        get() = Pager(pagingConfig) {
            PagingSource { page, pageSize ->
                interactor.getUserLikePhotos(
                    username = username,
                    page = page,
                    pageSize = pageSize
                )
            }
        }.mapState { image -> image.toPresentation() }

    val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(pagingConfig) {
            PagingSource { page, pageSize ->
                interactor.getUserCollections(
                    username = username,
                    page = page,
                    pageSize = pageSize
                )
            }
        }.mapState { collection -> collection.toPresentation() }

    fun popBackStack() {
        navigator.navigate(NavigationScreen.PopBackStack)
    }

    fun onUserClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(id: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(id))
    }

    fun onCollectionClick(id: String) {
        navigator.navigate(NavigationScreen.CollectionScreen(id))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}