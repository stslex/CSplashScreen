package com.stslex.csplashscreen.feature.home.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.collection.ui.toPresentation
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.core.ui.paging.PagingSource
import com.stslex.csplashscreen.feature.home.domain.MainScreenInteractor
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val interactor: MainScreenInteractor,
) : BaseViewModel() {

    val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllCollections)
        }
            .mapState { collection ->
                collection.toPresentation()
            }

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllPhotos)
        }
            .mapState { image ->
                image.toPresentation()
            }

    companion object {

        private val config = PagingConfig(
            pageSize = 3,
            enablePlaceholders = false
        )
    }
}