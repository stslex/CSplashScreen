package st.slex.csplashscreen.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharedFlow
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.main.UserImageHead
import st.slex.csplashscreen.ui.theme.Shapes
import st.slex.csplashscreen.ui.theme.Typography
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun ImageDetailScreen(
    url: String,
    navController: NavController,
    imageFlow: () -> SharedFlow<ImageModel>
) {
    val imageModel = imageFlow().collectAsState(initial = null).value
    BindDetailImage(navController, imageModel)
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun BindDetailImage(navController: NavController, imageModel: ImageModel?) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    val encodedUrl =
                        URLEncoder.encode(
                            imageModel?.urls?.regular,
                            StandardCharsets.UTF_8.toString()
                        )
                    navController.navigate("raw_image/$encodedUrl")
                },
            painter = rememberImagePainter(
                data = imageModel?.urls?.regular,
                builder = {
                    transformations(RoundedCornersTransformation())
                    allowHardware(false)
                }
            ),
            contentDescription = "TestImage"
        )

        Spacer(modifier = Modifier.padding(4.dp))

        UserImageHead(
            modifier = Modifier.padding(start = 16.dp),
            username = imageModel?.user?.username.toString(),
            url = imageModel?.user?.profile_image?.medium.toString(),
            navController = navController
        )
    }


}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CurrentImageUser(username: String, url: String, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 0.dp, Shapes.medium),
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
                    .size(48.dp)
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
                style = Typography.h5,
                maxLines = 1,
                lineHeight = TextUnit.Unspecified,
                fontFamily = FontFamily.SansSerif
            )
        }
    }

}

