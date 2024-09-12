package st.slex.csplashscreen.core.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import st.slex.csplashscreen.core.ui.components.ImageComponent
import st.slex.csplashscreen.core.ui.theme.Dimen

@Composable
fun PhotosBaseItem(
    onContainerClick: () -> Unit,
    onHeaderClick: () -> Unit,
    url: String,
    modifier: Modifier = Modifier,
    headerContent: @Composable RowScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val itemHeight = remember {
        configuration.screenHeightDp.dp / 3
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .padding(bottom = Dimen.medium)
            .clip(RoundedCornerShape(Dimen.medium))
            .clickable(
                onClick = onContainerClick,
//                    todo check this if needed
//                role = Role.Button,
//                interactionSource = remember { MutableInteractionSource() },
//                indication = rememberRipple()
            ),
    ) {
        ImageComponent(
            modifier = Modifier.fillMaxSize(),
            url = url,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .clickable(
                    onClick = onHeaderClick,
//                    todo check this if needed
//                    role = Role.Button,
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = rememberRipple()
                )
                .background(
                    color = MaterialTheme.colorScheme.background.copy(
                        alpha = 0.7f
                    )
                )
                .padding(Dimen.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            headerContent()
        }
        content()
    }
}