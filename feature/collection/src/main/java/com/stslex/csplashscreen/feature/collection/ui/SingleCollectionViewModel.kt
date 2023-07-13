package com.stslex.csplashscreen.feature.collection.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
    args: AppArguments.CollectionScreen,
    private val navigate: (NavigationScreen) -> Unit
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
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, imageId: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, imageId))
    }

    companion object {
        private val pagingConfig = PagingConfig(
            pageSize = 2,
            enablePlaceholders = false
        )
    }
}