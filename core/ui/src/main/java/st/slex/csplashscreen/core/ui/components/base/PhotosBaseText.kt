package st.slex.csplashscreen.core.ui.components.base

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import st.slex.csplashscreen.core.ui.components.ImageComponent

@Composable
fun MediumText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        lineHeight = TextUnit.Unspecified
    )
}

@Composable
fun SmallText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        lineHeight = TextUnit.Unspecified
    )
}

@Composable
fun MainUserAvatar(
    url: String,
    modifier: Modifier = Modifier,
) {
    ImageComponent(
        modifier = modifier
            .size(30.dp)
            .clip(RoundedCornerShape(8.dp)),
        url = url,
        contentScale = ContentScale.Crop,
    )
}