package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.google.accompanist.pager.ExperimentalPagerApi
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.UtilsExtensions.convertUrl
import st.slex.csplashscreen.ui.navigation.NavHostResource
import kotlin.math.absoluteValue


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
                val id = item.id
                val encodedUrl = item.urls.regular.convertUrl()
                val destination = NavHostResource.ImageDetailScreen.destination
                val route = "$destination/$encodedUrl/$id"
                navController.navigate(route)
            }
        ) {
            CoverPhotoItem(item.urls.regular)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CoverPhotoItem(url: String, modifier: Modifier = Modifier) {
    GlideImage(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds(),
        imageModel = url,
        contentScale = ContentScale.FillBounds,
        circularReveal = CircularReveal(duration = 1000),
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}