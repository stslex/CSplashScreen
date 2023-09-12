package st.slex.csplashscreen.feature.search.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.ui.model.SearchItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    private val navigator: Navigator,
    args: AppArguments.SearchPhotosScreen,
) : BaseViewModel() {

    private val _querySearch = MutableStateFlow(args.checkedQuery)
    val querySearch: StateFlow<String>
        get() = _querySearch.asStateFlow()

    val searchHistory: StateFlow<PagingData<SearchItem>>
        get() = interactor.searchHistory
            .primaryPagingFlow

    val photosSearch: StateFlow<PagingData<PhotoModel>>
        get() = querySearch
            .map(::newPagerPhotosSearch)
            .mapState { it.toPresentation() }

    private fun newPagerPhotosSearch(
        query: String
    ): Pager<Int, ImageModel> = Pager(config) {
        PagingSource { page, pageSize ->
            if (query.isBlank()) {
                emptyList()
            } else {
                interactor.getPhotos(query, page, pageSize)
            }
        }
    }

    fun setQueryPhotosSearch(query: String) {
        _querySearch.tryEmit(query)
    }

    fun onProfileClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(imageId: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(imageId))
    }

    fun clearHistory() {
        viewModelScope.launch {
            interactor.clearHistory()
        }
    }

    companion object {
        private val config = PagingConfig(
            pageSize = 3,
            enablePlaceholders = false
        )
    }
}