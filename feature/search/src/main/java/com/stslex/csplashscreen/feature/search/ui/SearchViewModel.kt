package com.stslex.csplashscreen.feature.search.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.network.model.ui.ImageModel
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.core.ui.paging.PagingSource
import com.stslex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import com.stslex.csplashscreen.feature.search.ui.model.SearchItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    args: AppArguments.SearchPhotosScreen,
    private val navigate: (NavigationScreen) -> Unit
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
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(imageId: String) {
        navigate(NavigationScreen.ImageDetailScreen(imageId))
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