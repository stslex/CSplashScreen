package st.slex.feature_main.ui.components.base

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.ui.components.ImageComponent


@Composable
fun MainUsername(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
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
            .size(60.dp)
            .clip(CircleShape),
        url = url,
        contentScale = ContentScale.Crop,
    )
}