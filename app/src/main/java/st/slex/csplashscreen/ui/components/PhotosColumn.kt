package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.core.logger.KOIN_TAG
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.navigation.NavHostResource
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.math.absoluteValue


@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun LazyPhotosColumn(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    navController: NavController
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(lazyPagingPhotosItems, key = { it.id }) { item ->
            item?.let { notNullImageModel ->
                ImageItem(
                    item = notNullImageModel,
                    navController = navController,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
                        .graphicsLayer {
                            val value =
                                1 - (lazyListState
                                    .layoutInfo
                                    .normalizedItemPosition(notNullImageModel.id)
                                    .absoluteValue * 0.05f)
                            alpha = value
                            scaleX = value
                            scaleY = value
                        }

                )
            }

        }
        lazyPagingPhotosItems.checkState(this)
    }
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItem(
    item: ImageModel,
    modifier: Modifier,
    navController: NavController,
    isUserVisible: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profile_image.medium,
                username = item.user.username,
                navController = navController
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        Card(
            onClick = {
                if (item.id.isNotEmpty() && item.urls.regular.isNotEmpty()) {
                    val url = item.urls.regular
                    val id = item.id
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    val destination = NavHostResource.ImageDetailScreen.destination
                    val route = "$destination/$encodedUrl/$id"
                    navController.navigate(route)
                } else Log.e(KOIN_TAG, "::is empty:")

            }
        ) {
            CoverPhotoItem(item.urls.regular)
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CoverPhotoItem(url: String, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds(),
        painter = rememberImagePainter(
            data = url,
            builder = {
                transformations(RoundedCornersTransformation())
                allowHardware(false)
                crossfade(500)
            }
        ),
        contentDescription = "Image",
    )
}