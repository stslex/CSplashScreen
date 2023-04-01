package st.slex.feature_photo_detail.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import st.slex.core_ui.theme.AppTheme

enum class DynamicButtonState {
    DEFAULT, EXPAND;
}

@Composable
fun DynamicButton(
    modifier: Modifier = Modifier
) {
    val isClicked = remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextButton(
            modifier = Modifier.height(100.dp),
            shape = ButtonDefaults.outlinedShape,
            onClick = {
                isClicked.value = isClicked.value.not()
            }
        ) {
            AnimatedVisibility(
                visible = isClicked.value.not(),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier,
                    text = "click me",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            AnimatedVisibility(
                visible = isClicked.value,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun DynamicButtonPreview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            DynamicButton(Modifier.padding(32.dp))
        }
    }
}