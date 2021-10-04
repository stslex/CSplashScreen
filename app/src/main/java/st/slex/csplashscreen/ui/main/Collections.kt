package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel?,
    navController: NavController,
    page: Int,
    scope: PagerScope
) {
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
            onClick = { navController.navigate("collection/${item?.id}") }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clipToBounds(),
                painter = rememberImagePainter(
                    data = item?.cover_photo?.urls?.regular.toString(),
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