package com.stslex.csplashscreen.feature.user.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.collection.ui.toPresentation
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.core.ui.paging.PagingSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import st.slex.core_network.model.ui.user.UserModel
import com.stslex.csplashscreen.feature.user.domain.UserInteractor

class UserViewModel(
    private val interactor: UserInteractor,
    private val navigate: (NavigationScreen) -> Unit,
    args: AppArguments.UserScreen,
) : BaseViewModel() {

    private val username: String = args.username

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
        navigate(NavigationScreen.PopBackStack)
    }

    fun onUserClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, id: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, id))
    }

    fun onCollectionClick(id: String) {
        navigate(NavigationScreen.CollectionScreen(id))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 3,
            enablePlaceholders = false
        )
    }
}