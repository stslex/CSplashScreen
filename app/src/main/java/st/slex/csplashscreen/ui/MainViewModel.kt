package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.core.Mapper
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.core.UIResponse
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.main.QueryCollectionsUseCase
import st.slex.csplashscreen.ui.main.QueryPhotosUseCase
import st.slex.csplashscreen.ui.search_photos.QuerySearchUseCase
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>,
    private val querySearchUseCaseProvider: Provider<QuerySearchUseCase>,
    private val repository: PhotoRepository,
    private val photoMapper: Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>>,
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

    /*Pager Queries*/
    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    private val _queryCollections = MutableStateFlow(emptyList<String>())
    private val queryCollections: StateFlow<List<String>> = _queryCollections.asStateFlow()

    private val _querySearch = MutableStateFlow<QuerySearch>(QuerySearch.EmptyQuery)
    private val querySearch: StateFlow<QuerySearch> = _querySearch.asStateFlow()

    /*Pager Result*/
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

    val photosSearch: StateFlow<PagingData<ImageModel>> = querySearch
        .map(::newPagerPhotosSearch)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    /*New Pager*/
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

    private fun newPagerPhotosSearch(query: QuerySearch): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingPhotosSearchSource?.invalidate()
            val queryPhotosUseCase = querySearchUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSearchSource = it }
        }
    }

    /*Set Queries*/
    fun setQueryCollections(query: List<String>) {
        _queryCollections.tryEmit(query)
    }

    fun setQueryPhotos(query: QueryPhotos) {
        _queryPhotos.tryEmit(query)
    }

    fun setQueryPhotosSearch(query: QuerySearch) {
        _querySearch.tryEmit(query)
    }


    /*Paging source*/
    private var newPagingSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null
    private var newPagingPhotosSearchSource: PagingSource<*, *>? = null

}