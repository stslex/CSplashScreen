package st.slex.csplashscreen.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.request.crossfade
import st.slex.scplashscreen.core.image.AppImageRequest.createImageRequestBuilder

@Composable
fun ImageComponent(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier,
        model = createImageRequestBuilder(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale
    )
}
