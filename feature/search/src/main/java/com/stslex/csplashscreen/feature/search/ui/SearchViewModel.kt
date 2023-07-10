package com.stslex.csplashscreen.feature.search.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import com.stslex.csplashscreen.feature.search.ui.model.SearchElement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import st.slex.core_network.model.ui.ImageModel

class SearchViewModel(
    private val interactor: SearchPhotosInteractor,
    args: AppArguments.SearchPhotosScreen,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    private val _querySearch = MutableStateFlow(args.checkedQuery)
    val querySearch: StateFlow<String>
        get() = _querySearch.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchHistory: StateFlow<PagingData<SearchElement>>
        get() = interactor.searchHistory
            .mapLatest { pagingData ->
                pagingData.map { item ->
                    SearchElement.Item(
                        item.query,
                        item.dateTime
                    )
                }
            }
            .mapLatest { pagingData ->
                pagingData
                    .insertSeparators { before, after ->
                        when {
                            after == null -> null
                            before == null -> SearchElement.Separator(after.dateTime)
                            before.dateTime.dayOfYear !=
                                    after.dateTime.dayOfYear
                            -> SearchElement.Separator(after.dateTime)

                            else -> null
                        }
                    }
            }
            .primaryPagingFlow

    val photosSearch: StateFlow<PagingData<PhotoModel>>
        get() = querySearch
            .map(::newPagerPhotosSearch)
            .mapState { it.toPresentation() }

    private fun newPagerPhotosSearch(
        query: String
    ): Pager<Int, ImageModel> = Pager(config) {
        SearchPagingSource { page, pageSize ->
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