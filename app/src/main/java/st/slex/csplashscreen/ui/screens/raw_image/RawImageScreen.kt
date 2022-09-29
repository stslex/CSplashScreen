package st.slex.csplashscreen.ui.screens.raw_image

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun RawImageScreen(
    navController: NavController,
    arguments: List<String>
) {
    val url: String = arguments[0]

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { navController.popBackStack() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds(),
            imageModel = url,
            contentScale = ContentScale.FillBounds,
            circularReveal = CircularReveal(duration = 1000),
            requestBuilder = {
                Glide.with(LocalContext.current.applicationContext).asDrawable()
            }
        )
    }
}