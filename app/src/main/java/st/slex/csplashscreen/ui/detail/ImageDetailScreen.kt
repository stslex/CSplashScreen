package st.slex.csplashscreen.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ImageDetailScreen(url: String) {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            painter = rememberImagePainter(
                data = url,
                builder = {
                    allowHardware(false)
                    crossfade(500)
                }
            ),
            contentDescription = "TestImage"
        )
        Text(text = "textProvider")
    }

}

@ExperimentalCoilApi
@Preview
@Composable
fun DetailScreenPreview() {
    ImageDetailScreen("")
}