package st.slex.feature_search_photos.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import st.slex.core_network.model.ui.ImageModel
import st.slex.feature_search_photos.data.QuerySearch
import st.slex.feature_search_photos.domain.SearchPhotosInteractor

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    args: AppArguments.SearchPhotosScreen,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    val rowQuery = args.checkedQuery.trimEnd()
    private val _querySearch = MutableStateFlow<QuerySearch>(QuerySearch.SearchPhotos(rowQuery))
    private val querySearch: StateFlow<QuerySearch> = _querySearch.asStateFlow()
    private var queryJob: Job? = null

    val photosSearch: StateFlow<PagingData<ImageModel>> = querySearch
        .map(::newPagerPhotosSearch)
        .pagingFlow

    private fun newPagerPhotosSearch(
        query: QuerySearch
    ): Pager<Int, ImageModel> = Pager(config) {
        interactor.getSearchPhotosPaging(query)
    }

    fun setQueryPhotosSearch(query: QuerySearch) {
        queryJob?.cancel()
        queryJob = viewModelScope.launch {
            delay(600)
            _querySearch.emit(query)
        }
    }

    fun onProfileClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, imageId: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, imageId))
    }

    companion object {
        private val config = PagingConfig(5, enablePlaceholders = false)
    }
}