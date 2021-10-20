package st.slex.csplashscreen.ui.screens.raw_image

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainActivity


@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun RawImageScreen(
    navController: NavController,
    url: String,
    viewModel: RawImageViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { navController.popBackStack() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = url,
                builder = {
                    allowHardware(false)
                    crossfade(500)
                }
            ),
            contentDescription = "TestImage"
        )
    }
}