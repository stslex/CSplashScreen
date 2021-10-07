package st.slex.csplashscreen.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.QueryPhotos
import javax.inject.Inject
import javax.inject.Provider

class MainScreenViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>
) : ViewModel() {

    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    private val _queryCollections = MutableStateFlow(emptyList<String>())
    private val queryCollections: StateFlow<List<String>> = _queryCollections.asStateFlow()

    val collections: StateFlow<PagingData<CollectionModel>> = queryCollections
        .map(::newPagerCollections)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val photos: StateFlow<PagingData<ImageModel>> = queryPhotos
        .map(::newPagerPhotos)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private var newPagingSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null

    fun setQueryCollections(query: List<String>) {
        _queryCollections.tryEmit(query)
    }

    fun setQueryPhotos(query: QueryPhotos) {
        _queryPhotos.tryEmit(query)
    }

    private fun newPagerCollections(query: List<String>): Pager<Int, CollectionModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingSource?.invalidate()
            val queryCollectionsUseCase = queryCollectionsUseCaseProvider.get()
            queryCollectionsUseCase(query).also { newPagingSource = it }
        }
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSource = it }
        }
    }
}