package st.slex.csplashscreen.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow
import st.slex.csplashscreen.R
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.UIResult
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun ImageDetailScreen(
    url: String,
    navController: NavController,
    imageFlow: () -> SharedFlow<UIResult<ImageModel>>
) {
    val imageModel = imageFlow().collectAsState(
        initial = UIResult.Loading,
        context = rememberCoroutineScope().coroutineContext
    ).value

    when (imageModel) {
        is UIResult.Success -> BindImage(navController, imageModel.data)
        is UIResult.Loading -> BindImageLoading(url = url)
        is UIResult.Failure -> BindImageFailure(url = url)
    }
}

@ExperimentalCoilApi
@Composable
private fun BindImage(navController: NavController, imageModel: ImageModel) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    val encodedUrl =
                        URLEncoder.encode(
                            imageModel.urls.regular,
                            StandardCharsets.UTF_8.toString()
                        )
                    navController.navigate("raw_image/$encodedUrl")
                },
            painter = rememberImagePainter(
                data = imageModel.urls.regular,
                builder = {
                    transformations(RoundedCornersTransformation())
                    allowHardware(false)
                }
            ),
            contentDescription = "TestImage"
        )
    }
}

@ExperimentalCoilApi
@Composable
private fun BindImageLoading(url: String) {
    androidx.compose.material.Surface(
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

@ExperimentalCoilApi
@Composable
private fun BindImageFailure(url: String) {

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        painter = rememberImagePainter(
            data = url,
            builder = {
                transformations(
                    RoundedCornersTransformation(),
                    BlurTransformation(LocalContext.current)
                )
                allowHardware(false)
                crossfade(500)
            }
        ),
        contentDescription = "TestImage"
    )
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        painter = rememberImagePainter(
            data = R.drawable.ic_baseline_sentiment_very_dissatisfied_24
        ),
        contentDescription = "TestImage"
    )
}

