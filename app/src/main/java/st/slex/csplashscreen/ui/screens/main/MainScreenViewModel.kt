package st.slex.csplashscreen.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.core.QueryCollections
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.QueryCollectionsUseCase
import st.slex.csplashscreen.ui.core.QueryPhotosUseCase
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class MainScreenViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>,
    private val _navigator: Navigator,
) : ViewModel() {

    init {
        _navigator.navigate(null)
    }

    val navigator: Navigator
        get() = _navigator

    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    private val _queryCollections = MutableStateFlow<QueryCollections>(QueryCollections.EmptyQuery)
    private val queryCollections: StateFlow<QueryCollections> = _queryCollections.asStateFlow()

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

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null

    fun setQueryCollections(query: QueryCollections) {
        _queryCollections.tryEmit(query)
    }

    fun setQueryPhotos(query: QueryPhotos) {
        _queryPhotos.tryEmit(query)
    }

    private fun newPagerCollections(query: QueryCollections): Pager<Int, CollectionModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingCollectionsSource?.invalidate()
            val queryCollectionsUseCase = queryCollectionsUseCaseProvider.get()
            queryCollectionsUseCase(query).also { newPagingCollectionsSource = it }
        }
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSource = it }
        }
    }
}