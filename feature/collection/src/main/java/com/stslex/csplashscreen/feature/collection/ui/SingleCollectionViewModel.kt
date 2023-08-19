package com.stslex.csplashscreen.feature.collection.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.navigator.Navigator
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.core.ui.paging.PagingSource
import com.stslex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import kotlinx.coroutines.flow.StateFlow

class SingleCollectionViewModel(
    private val interactor: SingleCollectionInteractor,
    private val navigator: Navigator,
    args: AppArguments.CollectionScreen,
) : BaseViewModel() {

    val photos: StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getPhotos(
                uuid = args.collectionId,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .mapState { image ->
            image.toPresentation()
        }

    fun onProfileClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(imageId: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(imageId))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 2,
            enablePlaceholders = false
        )
    }
}