package st.slex.feature_collection.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_photos.ui.QueryPhotosUseCase
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class SingleCollectionViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>
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