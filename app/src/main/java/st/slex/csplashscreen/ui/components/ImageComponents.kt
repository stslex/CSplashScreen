package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ImageUserCircle(url: String, modifier: Modifier) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = rememberImagePainter(
            data = url,
            builder = {
                allowHardware(false)
                crossfade(500)
            }
        ),
        contentDescription = "User Avatar"
    )
}