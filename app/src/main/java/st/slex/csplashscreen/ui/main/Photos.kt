package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.android.material.animation.AnimationUtils
import st.slex.csplashscreen.data.model.ui.image.ImageModel


@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItem(item: ImageModel?, navController: NavController, page: Int, scope: PagerScope) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .graphicsLayer {
                val pageOffset = scope.calculateCurrentOffsetForPage(page)
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
                // We animate the alpha, between 50% and 100%
                alpha = AnimationUtils.lerp(
                    0.5f,
                    1f,
                    1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .aspectRatio(1f)) {
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                painter = rememberImagePainter(
                    data = item?.user?.profile_image?.medium.toString(),
                    builder = {
                        allowHardware(false)
                        crossfade(500)
                    }
                ),
                contentDescription = "User Avatar"
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = item?.user?.username.toString()
            )
        }

        Card(
            modifier = Modifier.shadow(elevation = 8.dp, clip = true),
            onClick = { navController.navigate("detail") }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clipToBounds(),
                painter = rememberImagePainter(
                    data = item?.urls?.regular.toString(),
                    builder = {
                        transformations(RoundedCornersTransformation())
                        allowHardware(false)
                        crossfade(500)
                    }
                ),
                contentDescription = "Image",
            )
        }
    }
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItemLoading(page: Int, scope: PagerScope) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Card(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    )
            ) {}
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    ),
                text = "User name placeholder"
            )
        }

        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = scope.calculateCurrentOffsetForPage(page)
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
                    // We animate the alpha, between 50% and 100%
                    alpha = AnimationUtils.lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .aspectRatio(1f)
                .shadow(elevation = 8.dp, clip = true)
        ) {
            Surface(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    )
            ) {}
        }
    }
}
