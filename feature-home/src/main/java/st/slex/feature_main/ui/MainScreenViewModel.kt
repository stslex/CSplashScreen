package st.slex.feature_main.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_collection.data.QueryCollections
import st.slex.core_collection.ui.QueryCollectionsUseCase
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_photos.ui.QueryPhotosUseCase
import st.slex.core_ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>
) : BaseViewModel() {

    private val newPagerCollections: Pager<Int, CollectionModel> by lazy {
        Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingCollectionsSource?.invalidate()
            val queryCollectionsUseCase = queryCollectionsUseCaseProvider.get()
            queryCollectionsUseCase(QueryCollections.AllCollections).also {
                newPagingCollectionsSource = it
            }
        }
    }

    private val newPagerPhotos: Pager<Int, ImageModel> by lazy {
        Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(QueryPhotos.AllPhotos).also { newPagingPhotosSource = it }
        }
    }

    val collections: StateFlow<PagingData<CollectionModel>> =
        newPagerCollections.flow.makeStateFlow(PagingData.empty())

    val photos: StateFlow<PagingData<ImageModel>> =
        newPagerPhotos.flow.makeStateFlow(PagingData.empty())

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null
}