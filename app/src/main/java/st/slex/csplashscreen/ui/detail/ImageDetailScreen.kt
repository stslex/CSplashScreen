package st.slex.csplashscreen.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@Composable
fun ImageDetailScreen(
    url: String,
    imageId: String,
    viewModel: MainViewModel,
    navController: NavController,
) {
    val stateFlow = viewModel.getCurrentPhoto(imageId)
    val viewState =
        viewModel.getCurrentPhoto(imageId).collectAsState(rememberCoroutineScope().coroutineContext)
    Log.i("THisIsResult", viewState.toString())
    val result = viewState.value
    /*when (val result = viewState.value) {
        is UIResult.Success -> {
            viewState.wait().notifyAll()
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
                        data = result.data.urls.regular,
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
        is UIResult.Loading -> {

        }
        is UIResult.Failure -> {

        }
    }*/


}