package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.core.Mapper
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.DownloadModel
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.ui.core.UIResponse
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.main.QueryCollectionsUseCase
import st.slex.csplashscreen.ui.main.QueryPhotosUseCase
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>,
    private val repository: PhotoRepository,
    private val photoMapper: Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>>,
    private val downloadMapper: Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>>,
    private val response: UIResponse
) : ViewModel() {

    private val _currentPhoto = MutableSharedFlow<UIResult<ImageModel>>(replay = 0)
    val currentPhoto: SharedFlow<UIResult<ImageModel>>
        get() = _currentPhoto

    fun getCurrentPhoto(id: String) = viewModelScope.launch {
        repository.getCurrentPhoto(id).collect {
            response.getAndMap(repository.getCurrentPhoto(id), mapper = photoMapper).collect {
                _currentPhoto.emit(it)
            }
        }
    }

    suspend fun downloadPhoto(id: String): StateFlow<UIResult<DownloadModel>> =
        response.getAndMap(repository.downloadPhoto(id), downloadMapper).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    private val _queryPhotos = MutableStateFlow(emptyList<String>())
    private val queryPhotos: StateFlow<List<String>> = _queryPhotos.asStateFlow()

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
    private val queryCollections: StateFlow<List<String>> = _queryCollections.asStateFlow()

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