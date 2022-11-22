package st.slex.feature_search_photos.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import st.slex.core_ui.components.ListOfElements
import st.slex.feature_search_photos.data.QuerySearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotosScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel()
) {
    val lazyPagingPhotosItems = viewModel.photosSearch.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarSearch(querySearch = viewModel.rowQuery) {
                viewModel.setQueryPhotosSearch(it)
            }
        }
    ) { paddingValues ->
        ListOfElements(
            modifier = Modifier.padding(paddingValues),
            lazyPagingPhotosItems = lazyPagingPhotosItems,
            onProfileClick = viewModel::onProfileClick,
            onImageClick = viewModel::onImageClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarSearch(querySearch: String, search: (QuerySearch) -> Unit) {
    var textInput by remember { mutableStateOf(querySearch) }
    val focusRequester = remember { FocusRequester() }
    if (querySearch.isEmpty()) {
        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            onDispose { }
        }
    }
    TopAppBar(
        title = {
            TextField(
                value = textInput,
                onValueChange = { text ->
                    textInput = text
                    search(QuerySearch.SearchPhotos(textInput))
                    focusRequester.requestFocus()
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                singleLine = true,
                label = { Text(text = "Input Search") },
                textStyle = MaterialTheme.typography.bodyMedium
            )

        }
    )
}

