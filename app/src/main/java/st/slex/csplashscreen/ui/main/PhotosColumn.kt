package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.theme.Shapes
import st.slex.csplashscreen.ui.theme.Typography
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItem(
    item: ImageModel?,
    navController: NavController,
    page: Int = 0,
    scope: PagerScope? = null
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
            .graphicsLayer {
                scope?.let {
                    val pageOffset = it.calculateCurrentOffsetForPage(page)
                    AnimationUtils
                        .lerp(
                            0.85f,
                            1f,
                            1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    alpha = AnimationUtils.lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            }
            .fillMaxWidth()
            .aspectRatio(1f)) {

        UserImageHead(
            modifier = Modifier,
            url = item?.user?.profile_image?.medium.toString(),
            username = item?.user?.username.toString(),
            navController = navController
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(32.dp))
                .shadow(elevation = 16.dp, shape = Shapes.medium),
            onClick = {
                val url = item?.urls?.regular
                val id = item?.id
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                navController.navigate("detail/$encodedUrl/$id")
            }
        ) {
            CoverPhotoItem(item?.urls?.regular.toString())
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UserImageHead(modifier: Modifier, url: String, username: String, navController: NavController) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp, Shapes.medium)
            .clip(RoundedCornerShape(16.dp)),
        onClick = {
            //navController.navigate("user_profile")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                painter = rememberImagePainter(
                    data = url,
                    builder = {
                        allowHardware(false)
                        crossfade(500)
                    }
                ),
                contentDescription = "User Avatar"
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = username,
                style = Typography.h6,
                maxLines = 1,
                lineHeight = TextUnit.Unspecified,
                fontFamily = FontFamily.SansSerif
            )
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