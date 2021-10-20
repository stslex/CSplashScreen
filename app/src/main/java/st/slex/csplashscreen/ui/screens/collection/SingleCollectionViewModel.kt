package st.slex.csplashscreen.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.QueryPhotosUseCase
import javax.inject.Inject
import javax.inject.Provider

class SingleCollectionViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
) : ViewModel() {

    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    @ExperimentalCoroutinesApi
    val photos: StateFlow<PagingData<ImageModel>> = queryPhotos
        .map(::newPagerPhotos)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private var newPagingPhotosSource: PagingSource<*, *>? = null

    fun setQueryPhotos(query: QueryPhotos) {
        _queryPhotos.tryEmit(query)
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSource = it }
        }
    }
}