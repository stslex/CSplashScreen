package st.slex.csplashscreen.ui.screens.search_photos

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.androidx.AndroidScreen
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.components.LazyPhotosColumn
import st.slex.csplashscreen.ui.components.setTextFieldColors
import st.slex.csplashscreen.ui.theme.Typography
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
class SearchPhotosScreen(private val query: String) : AndroidScreen() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @ExperimentalAnimationApi
    @Composable
    override fun Content() {
        LocalContext.current.applicationContext.appComponent.inject(this)
        val viewModel: SearchViewModel = viewModel(factory = viewModelFactory.get())
        val checkedQuery = if (query == " ") "" else query
        viewModel.setQueryPhotosSearch(QuerySearch.SearchPhotos(checkedQuery))
        val lazyPagingPhotosItems = viewModel.photosSearch.collectAsLazyPagingItems()
        Scaffold(
            topBar = {
                TopAppBarSearch(querySearch = checkedQuery) {
                    viewModel.setQueryPhotosSearch(it)
                }
            }
        ) {
            LazyPhotosColumn(lazyPagingPhotosItems = lazyPagingPhotosItems)
        }
    }
}

@Composable
private fun TopAppBarSearch(querySearch: String, search: (QuerySearch) -> Unit) {
    TopAppBar {
        var textInput by remember { mutableStateOf(querySearch) }
        val focusRequester = remember { FocusRequester() }
        TextField(
            value = textInput,
            onValueChange = { text ->
                textInput = text
                search(QuerySearch.SearchPhotos(textInput))
                focusRequester.requestFocus()
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            colors = setTextFieldColors(),
            maxLines = 1,
            label = { Text(text = "Input Search") },
            textStyle = Typography.body2
        )
        if (querySearch.isEmpty()) {
            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                onDispose { }
            }
        }
    }
}

