package st.slex.csplashscreen.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class PhotosViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>
) : ViewModel() {

    private val _query = MutableStateFlow(emptyList<String>())
    val query: StateFlow<List<String>> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    val photos: StateFlow<PagingData<ImageModel>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: List<String>): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingSource = it }
        }
    }

    fun setQuery(query: List<String>) {
        _query.tryEmit(query)
    }
}