package st.slex.core_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import st.slex.core_ui.R

@Composable
fun TestCoverPhotoItem(
    modifier: Modifier = Modifier,
    url: String
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds(),
        painter = painterResource(id = R.drawable.test_cover),
        contentDescription = url
    )
}

@Composable
fun CoverBlurItem(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        modifier = modifier,
        imageModel = url,
        alpha = 0.9f,
        contentScale = ContentScale.Crop,
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}

@Composable
fun TestCoverBlurItem(
    modifier: Modifier = Modifier,
    url: String
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        painter = painterResource(id = R.drawable.test_cover),
        contentDescription = url
    )
}

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