package st.slex.csplashscreen.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.image.TagModel
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun ImageDetailScreen(
    url: String,
    navController: NavController,
    imageFlow: () -> SharedFlow<UIResult<ImageModel>>
) {
    val uiResult = imageFlow().collectAsState(initial = null).value

    Column {
        BindTopImageHead(url = url, navController = navController)
        Spacer(modifier = Modifier.padding(4.dp))
        when (uiResult) {
            is UIResult.Success -> {
                BindDetailImageBody(image = uiResult.data, navController = navController)
            }
            is UIResult.Loading -> {
                BindDetailImageLoading(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is UIResult.Failure -> {
                BindDetailImageFailure()
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun BindTopImageHead(url: String, navController: NavController) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                navController.navigate("raw_image/$encodedUrl")
            },
        painter = rememberImagePainter(
            data = url,
            builder = {
                transformations(RoundedCornersTransformation())
                allowHardware(false)
            }
        ),
        contentDescription = "Image"
    )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun BindDetailImageBody(image: ImageModel, navController: NavController) {
    UserImageHeadWithUserName(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        username = image.user?.username.toString(),
        url = image.user?.profile_image?.medium.toString(),
        navController = navController
    )
    Spacer(modifier = Modifier.padding(4.dp))
    if (!image.tags.isNullOrEmpty()) BindDetailImageBodyTags(image.tags)
    Spacer(modifier = Modifier.padding(4.dp))
}

@ExperimentalMaterialApi
@Composable
private fun BindDetailImageBodyTags(tags: List<TagModel>) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .shadow(elevation = 8.dp, Shapes.medium)
            .clip(RoundedCornerShape(16.dp))
    ) {
        LazyRow(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = tags.size) { key ->
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .shadow(elevation = 4.dp, Shapes.medium)
                        .clip(RoundedCornerShape(8.dp)),
                    onClick = {

                    }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = tags[key].title.toString()
                    )
                }
            }
        }
    }
}

@Composable
private fun BindDetailImageLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
private fun BindDetailImageFailure() {

}
