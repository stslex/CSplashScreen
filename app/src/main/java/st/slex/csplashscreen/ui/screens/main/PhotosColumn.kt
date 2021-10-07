package st.slex.csplashscreen.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.navigation.NavigationState
import st.slex.csplashscreen.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun LazyPhotosColumn(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    navigation: (NavigationState, List<String>) -> Unit
) {
    LazyColumn {
        items(lazyPagingPhotosItems) { item ->
            ImageItem(
                item = item,
                navigation = navigation,
                modifier = Modifier
            )
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
    item: ImageModel?,
    modifier: Modifier,
    navigation: (NavigationState, List<String>) -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {

        UserImageHeadWithUserName(
            modifier = Modifier.fillMaxWidth(),
            url = item?.user?.profile_image?.medium.toString(),
            username = item?.user?.username.toString(),
            navigation = navigation
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .shadow(elevation = 16.dp, shape = Shapes.medium),
            onClick = {
                val url = item?.urls?.regular.toString()
                val id = item?.id.toString()
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                val args = listOf(encodedUrl, id)
                navigation(NavigationState.ImageDetailScreen, args)
            }
        ) {
            CoverPhotoItem(item?.urls?.regular.toString())
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CoverPhotoItem(url: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(32.dp))
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