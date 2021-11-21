package st.slex.csplashscreen.ui.screens.search_photos

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.components.LazyPhotosColumn
import st.slex.csplashscreen.ui.components.setTextFieldColors
import st.slex.csplashscreen.ui.theme.Typography

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun SearchPhotosScreen(
    navController: NavController,
    arguments: List<String>,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query: String = arguments[0]
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
        LazyPhotosColumn(
            lazyPagingPhotosItems = lazyPagingPhotosItems,
            navController = navController
        )
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

