package st.slex.csplashscreen.ui.screens.search_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.search.QuerySearch
import javax.inject.Inject
import javax.inject.Provider

class SearchViewModel @Inject constructor(
    private val querySearchUseCaseProvider: Provider<QuerySearchUseCase>
) : ViewModel() {

    private val _querySearch = MutableStateFlow<QuerySearch>(QuerySearch.EmptyQuery)
    private val querySearch: StateFlow<QuerySearch> = _querySearch.asStateFlow()

    val photosSearch: StateFlow<PagingData<ImageModel>> = querySearch
        .map(::newPagerPhotosSearch)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPagerPhotosSearch(query: QuerySearch): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingPhotosSearchSource?.invalidate()
            val queryPhotosUseCase = querySearchUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSearchSource = it }
        }
    }

    fun setQueryPhotosSearch(query: QuerySearch) {
        _querySearch.tryEmit(query)
    }

    private var newPagingPhotosSearchSource: PagingSource<*, *>? = null
}