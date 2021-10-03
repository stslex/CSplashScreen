package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import st.slex.csplashscreen.utiles.GET_PHOTOS

@ExperimentalCoilApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoroutinesApi
@Composable
fun MainScreen(navController: NavController, viewModel: PhotosViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val query = listOf(GET_PHOTOS)
    coroutineScope.launch {
        viewModel.setQuery(query)
    }

    val lazyPagingItems = viewModel.photos.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPagingItems) {
            Image(
                painter = rememberImagePainter(
                    data = it?.urls?.regular.toString(),
                    builder = {
                        transformations(RoundedCornersTransformation(32F))
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clipToBounds()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("detail")
                    }
            )
        }
    }
}
