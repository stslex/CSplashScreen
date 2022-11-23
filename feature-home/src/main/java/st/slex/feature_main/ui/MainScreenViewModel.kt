package st.slex.feature_main.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_main.domain.MainScreenInteractor
import st.slex.feature_main.navigation.MainScreenRouter

class MainScreenViewModel(
    private val interactor: MainScreenInteractor,
    private val router: MainScreenRouter
) : BaseViewModel() {

    private val newPagerCollections: Pager<Int, CollectionModel> by lazy {
        Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingCollectionsSource?.invalidate()
            interactor.getCollectionsPagingSource(QueryCollections.AllCollections).also {
                newPagingCollectionsSource = it
            }
        }
    }

    private val newPagerPhotos: Pager<Int, ImageModel> by lazy {
        Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            interactor.getPhotosPagingSource(QueryPhotos.AllPhotos).also {
                newPagingPhotosSource = it
            }
        }
    }

    val collections: StateFlow<PagingData<CollectionModel>> = newPagerCollections.pagingFlow

    val photos: StateFlow<PagingData<ImageModel>> = newPagerPhotos.pagingFlow

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null

    fun onProfileClick(username: String) {
        router.navToProfile(username)
    }

    fun onImageClick(url: String, imageId: String) {
        router.navToDetailImage(url, imageId)
    }

    fun onCollectionClick(id: String) {
        router.navToSingleCollection(id)
    }
}