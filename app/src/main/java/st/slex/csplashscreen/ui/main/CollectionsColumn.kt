package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.ui.theme.Shapes
import st.slex.csplashscreen.ui.theme.TransparentGray
import st.slex.csplashscreen.ui.theme.Typography

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel?,
    navController: NavController,
    page: Int = 0,
    scope: PagerScope? = null
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
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
                .shadow(elevation = 16.dp, shape = Shapes.medium, clip = true),
            onClick = { navController.navigate("collection/${item?.id}") },
        ) {
            BindCoverImageConstraint(
                item?.cover_photo?.urls?.regular.toString(),
                item?.title.toString(),
                item?.total_photos.toString()
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindCoverImageConstraint(
    url: String,
    title: String,
    totalPhotos: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        val (background, content) = createRefs() // 1

        CoverPhotoItem(url)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .constrainAs(background) {
                    centerTo(parent)
                },
            color = TransparentGray
        ) {}

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .constrainAs(content) {
                bottom.linkTo(parent.bottom)
            }) {
            CollectionTextCard(text = title)
            Spacer(modifier = Modifier.padding(8.dp))
            CollectionTextCard(text = "${totalPhotos} Photos")
        }
    }
}

@Composable
fun CollectionTextCard(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        color = Color.White,
        style = Typography.h3,
        maxLines = 1
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CoverPhotoItem(url: String) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
            .background(Color.Black),
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