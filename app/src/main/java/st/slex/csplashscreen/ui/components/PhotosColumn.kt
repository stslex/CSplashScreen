package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import kotlin.math.absoluteValue


@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun LazyPhotosColumn(lazyPagingPhotosItems: LazyPagingItems<ImageModel>) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(lazyPagingPhotosItems, key = { it.id }) { item ->
            item?.let { notNullImageModel ->
                ImageItem(
                    item = notNullImageModel,
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

@ExperimentalCoroutinesApi
@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItem(
    item: ImageModel,
    modifier: Modifier,
    isUserVisible: Boolean = true,
    navigator: Navigator = LocalNavigator.currentOrThrow
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
                navigator = navigator
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        Card(
            onClick = {
                val url = item.urls.regular
                val id = item.id
                val imageScreen = ImageDetailScreen(url, id)
                navigator.push(imageScreen)
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