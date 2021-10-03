package st.slex.csplashscreen.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import st.slex.csplashscreen.R

@Composable
fun ImageDetailScreen() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "TestImage"
        )
        Text(text = "textProvider")
    }

}

@Preview
@Composable
fun DetailScreenPreview() {
    ImageDetailScreen()
}