package st.slex.csplashscreen.ui.screens.search_photos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.core_ui.components.ListOfElements
import st.slex.core_ui.components.setTextFieldColors
import st.slex.core_ui.theme.Typography
import st.slex.csplashscreen.data.search.QuerySearch

@Composable
fun SearchPhotosScreen(
    arguments: List<String>,
    viewModel: SearchViewModel = hiltViewModel(),
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit
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
    ) { paddingValues ->
        ListOfElements(
            modifier = Modifier.padding(paddingValues),
            lazyPagingPhotosItems = lazyPagingPhotosItems,
            onProfileClick = onProfileClick,
            onImageClick = onImageClick
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
            textStyle = Typography.bodyMedium
        )
        if (querySearch.isEmpty()) {
            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                onDispose { }
            }
        }
    }
}

