package st.slex.csplashscreen.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
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
    imageFlow: () -> StateFlow<UIResult<ImageModel>>
) {
    val imageModel = imageFlow().collectAsState().value

    when (imageModel) {
        is UIResult.Success -> {
            BindImage(navController, url, imageModel.data)
        }
    }

    Log.i("imageModel", imageModel.toString())
}

@Composable
fun BindImage(navController: NavController, url: String, imageModel: ImageModel) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    val encodedUrl =
                        URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate("raw_image/$encodedUrl")
                },
            painter = rememberImagePainter(
                data = imageModel.urls.regular,
                builder = {
                    transformations(RoundedCornersTransformation())
                    allowHardware(false)
                    crossfade(500)
                }
            ),
            contentDescription = "TestImage"
        )
    }
}