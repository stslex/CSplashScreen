package st.slex.csplashscreen.ui.search_photos

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.ui.MainViewModel
import st.slex.csplashscreen.ui.main.ImageItem
import st.slex.csplashscreen.ui.main.checkState
import st.slex.csplashscreen.ui.main.loadState

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
        ColumnCreate(
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

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ColumnCreate(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    navController: NavController,
) {
    LazyColumn {
        items(lazyPagingPhotosItems) { item ->
            ImageItem(item = item, navController = navController)
            Log.i("ImageItem: ", item.toString())
        }
        lazyPagingPhotosItems.checkState { loadState() }
    }
}

@Composable
fun MyAppTextFieldColors(
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.DarkGray,
    backgroundColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.Black,
    focusedIndicatorColor: Color = Color.Black,
    unfocusedIndicatorColor: Color = Color.Black,
    disabledIndicatorColor: Color = Color.Black,
    errorIndicatorColor: Color = Color.Black,
    leadingIconColor: Color = Color.Black,
    disabledLeadingIconColor: Color = Color.Black,
    errorLeadingIconColor: Color = Color.Black,
    trailingIconColor: Color = Color.Black,
    disabledTrailingIconColor: Color = Color.Black,
    errorTrailingIconColor: Color = Color.Black,
    focusedLabelColor: Color = Color.Black,
    unfocusedLabelColor: Color = Color.Black,
    disabledLabelColor: Color = Color.Black,
    errorLabelColor: Color = Color.Black,
    placeholderColor: Color = Color.Black,
    disabledPlaceholderColor: Color = Color.Black
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    focusedIndicatorColor = focusedIndicatorColor,
    unfocusedIndicatorColor = unfocusedIndicatorColor,
    disabledIndicatorColor = disabledIndicatorColor,
    errorIndicatorColor = errorIndicatorColor,
    leadingIconColor = leadingIconColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    errorLeadingIconColor = errorLeadingIconColor,
    trailingIconColor = trailingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
    errorTrailingIconColor = errorTrailingIconColor,
    focusedLabelColor = focusedLabelColor,
    unfocusedLabelColor = unfocusedLabelColor,
    disabledLabelColor = disabledLabelColor,
    errorLabelColor = errorLabelColor,
    placeholderColor = placeholderColor,
    disabledPlaceholderColor = disabledPlaceholderColor
)
