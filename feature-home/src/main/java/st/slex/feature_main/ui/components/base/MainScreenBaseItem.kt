package st.slex.feature_main.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.ui.components.ImageComponent

@Composable
fun MainScreenBaseItem(
    onContainerClick: () -> Unit,
    onHeaderClick: () -> Unit,
    url: String,
    modifier: Modifier = Modifier,
    headerContent: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(30.dp))
            .clickable(
                onClick = onContainerClick,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
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
                    role = Role.Button,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                )
                .background(
                    color = MaterialTheme.colorScheme.background.copy(
                        alpha = 0.7f
                    )
                )
                .padding(16.dp)
                .shadow(elevation = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            headerContent()
        }
    }
}