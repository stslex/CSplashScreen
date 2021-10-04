package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils.lerp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.utiles.GET_PHOTOS

@ExperimentalMaterialApi
@ExperimentalPagerApi
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
    Pager(lazyPagingItems, navController)
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun Pager(lazyPagingItems: LazyPagingItems<ImageModel>, navController: NavController) {
    HorizontalPager(
        count = 4,
        contentPadding = PaddingValues(),
    ) { page ->
        LazyColumn {
            items(lazyPagingItems) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page)
                            lerp(
                                0.85f,
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                0.5f,
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .aspectRatio(1f)
                        .shadow(elevation = 8.dp, clip = true),
                    onClick = { navController.navigate("detail") }
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = it?.urls?.regular.toString(),
                            builder = {
                                transformations(RoundedCornersTransformation())
                                allowHardware(false)
                                crossfade(500)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clipToBounds()
                    )
                }

            }
        }
    }
}
