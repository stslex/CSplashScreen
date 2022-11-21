package st.slex.feature_search_photos.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import st.slex.core_navigation.routers.ImageRouter
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_search_photos.data.QuerySearch
import st.slex.feature_search_photos.domain.SearchPhotosInteractor

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    private val router: ImageRouter
) : BaseViewModel() {

    private val _querySearch = MutableStateFlow<QuerySearch>(QuerySearch.EmptyQuery)
    private val querySearch: StateFlow<QuerySearch> = _querySearch.asStateFlow()

    val photosSearch: StateFlow<PagingData<ImageModel>> = querySearch
        .map(::newPagerPhotosSearch)
        .pagingFlow

    private fun newPagerPhotosSearch(query: QuerySearch): Pager<Int, ImageModel> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            newPagingPhotosSearchSource?.invalidate()
            interactor.getSearchPhotosPaging(query).also { newPagingPhotosSearchSource = it }
        }
    }

    fun setQueryPhotosSearch(query: QuerySearch) {
        _querySearch.tryEmit(query)
    }

    private var newPagingPhotosSearchSource: PagingSource<*, *>? = null

    fun onProfileClick(username: String) {
        router.navToProfile(username)
    }

    fun onImageClick(url: String, imageId: String) {
        router.navToDetailImage(url, imageId)
    }
}