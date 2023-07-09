package com.stslex.csplashscreen.feature.search.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.search.domain.SearchPhotosInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import st.slex.core_network.model.ui.ImageModel

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    args: AppArguments.SearchPhotosScreen,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    private val _querySearch = MutableStateFlow(args.checkedQuery)
    val querySearch: StateFlow<String>
        get() = _querySearch.asStateFlow()

    val photosSearch: StateFlow<PagingData<PhotoModel>>
        get() = querySearch
            .map(::newPagerPhotosSearch)
            .mapState { it.toPresentation() }

    private fun newPagerPhotosSearch(
        query: String
    ): Pager<Int, ImageModel> = Pager(config) {
        SearchPagingSource { page, pageSize ->
            interactor.getPhotos(query, page, pageSize)
        }
    }

    fun setQueryPhotosSearch(query: String) {
        _querySearch.tryEmit(query)
    }

    fun onProfileClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, imageId: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, imageId))
    }

    companion object {
        private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}