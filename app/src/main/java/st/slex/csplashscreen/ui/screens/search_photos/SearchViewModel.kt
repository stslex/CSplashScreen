package st.slex.csplashscreen.ui.screens.search_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.navigation.NavigationActions
import st.slex.csplashscreen.ui.navigation.NavigationState
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class SearchViewModel @Inject constructor(
    private val navigator: Navigator,
    private val actions: NavigationActions,
    private val querySearchUseCaseProvider: Provider<QuerySearchUseCase>
) : ViewModel() {

    fun navigate(destination: NavigationState, args: List<String>) {
        navigator.navigate(actions.navigation(destination, args))
    }

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