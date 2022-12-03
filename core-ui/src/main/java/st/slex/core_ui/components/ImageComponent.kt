package st.slex.core_ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CoverPhotoItem(
    modifier: Modifier = Modifier,
    url: String
) {
    ImageComponent(
        url = url,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
    )
}

@Composable
fun CoverUserPhotoItemBlur(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds(),
        imageModel = url,
        contentScale = ContentScale.None
    )
}

@Composable
fun ImageComponent(url: String, modifier: Modifier) {
    GlideImage(
        modifier = modifier,
        imageModel = url,
        contentScale = ContentScale.FillBounds,
        circularReveal = CircularReveal(duration = 700),
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}