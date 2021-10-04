package st.slex.csplashscreen.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>
) : ViewModel() {

    private val _queryPhotos = MutableStateFlow(emptyList<String>())
    val queryPhotos: StateFlow<List<String>> = _queryPhotos.asStateFlow()

    private var photosPagingSource: PagingSource<*, *>? = null

    val photos: StateFlow<PagingData<ImageModel>> = queryPhotos
        .map(::newPhotosPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPhotosPager(query: List<String>): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            photosPagingSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { photosPagingSource = it }
        }
    }

    fun setQueryPhotos(query: List<String>) {
        _queryPhotos.tryEmit(query)
    }

    private val _queryCollections = MutableStateFlow(emptyList<String>())
    val queryCollections: StateFlow<List<String>> = _queryCollections.asStateFlow()

    private var newCollectionsPagingSource: PagingSource<*, *>? = null

    val collections: StateFlow<PagingData<CollectionModel>> = queryCollections
        .map(::newPagerCollections)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPagerCollections(query: List<String>): Pager<Int, CollectionModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newCollectionsPagingSource?.invalidate()
            val queryPhotosUseCase = queryCollectionsUseCaseProvider.get()
            queryPhotosUseCase(query).also { newCollectionsPagingSource = it }
        }
    }

    fun setQueryCollections(query: List<String>) {
        _queryCollections.tryEmit(query)
    }
}