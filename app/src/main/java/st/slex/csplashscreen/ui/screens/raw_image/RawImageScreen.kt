package st.slex.csplashscreen.ui.screens.raw_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter


@ExperimentalCoilApi
class RawImageScreen(private val url: String) : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .clickable { navigator.pop() },
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
}