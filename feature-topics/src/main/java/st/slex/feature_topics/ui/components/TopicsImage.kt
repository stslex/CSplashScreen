package st.slex.feature_topics.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun TopicsImage(
    isSelectedState: Boolean,
    url: String,
    id: String,
    modifier: Modifier = Modifier
) {
    val blurSize by animateDpAsState(
        targetValue = if (isSelectedState) 16.dp else 0.dp
    )
    val colorFilter by animateFloatAsState(
        targetValue = if (isSelectedState) 0.5f else 1f
    )

    AsyncImage(
        modifier = modifier
            .aspectRatio(1f)
            .blur(
                radius = blurSize,
                edgeTreatment = BlurredEdgeTreatment.Rectangle
            )
            .alpha(colorFilter),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .memoryCacheKey(id)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}