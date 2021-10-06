package st.slex.csplashscreen.ui.search_photos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.MainViewModel
import st.slex.csplashscreen.ui.collection.LazyCollectionPhotosColumn
import st.slex.csplashscreen.ui.components.MyAppTextFieldColors

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun SearchPhotosScreen(
    viewModel: MainViewModel,
    navController: NavController,
    querySearch: String
) {

    viewModel.setQueryPhotosSearch(QuerySearch.SearchPhotos(querySearch))
    val lazyPagingPhotosItems = viewModel.photosSearch.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBarSearch(querySearch = querySearch) { viewModel.setQueryPhotosSearch(it) } }
    ) {
        LazyCollectionPhotosColumn(
            lazyPagingPhotosItems = lazyPagingPhotosItems,
            navController = navController
        )
    }

}


@Composable
fun TopAppBarSearch(querySearch: String, search: (QuerySearch) -> Unit) {
    TopAppBar {
        var textInput by remember { mutableStateOf(querySearch) }
        TextField(
            value = textInput,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { text ->
                textInput = text
                search(QuerySearch.SearchPhotos(textInput))
            },
            colors = MyAppTextFieldColors(),
            label = { Text(text = "Input Search") }
        )
    }
}

