package st.slex.feature_photo_detail.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import st.slex.feature_photo_detail.R
import java.util.concurrent.TimeUnit

enum class ButtonState {
    DEFAULT, EXPAND;

    fun click(): ButtonState = when (this) {
        DEFAULT -> EXPAND
        EXPAND -> DEFAULT
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    onDownloadImageClick: () -> Unit
) {
    val buttonState = remember {
        mutableStateOf(ButtonState.DEFAULT)
    }

    var expandJob: Job? = null

    LaunchedEffect(key1 = buttonState.value == ButtonState.EXPAND) {
        expandJob = launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            buttonState.value = ButtonState.DEFAULT
        }
    }

    Surface(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .wrapContentSize()
            .padding(start = 8.dp, end = 8.dp)
            .shadow(elevation = 16.dp, shape = CircleShape),
        shape = CircleShape,
        onClick = {
            if (buttonState.value == ButtonState.EXPAND) {
                onDownloadImageClick()
            } else {
                expandJob?.cancel()
            }
            buttonState.value = buttonState.value.click()
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_download),
                contentDescription = "Download",
                tint = MaterialTheme.colorScheme.onSurface
            )
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
                visible = buttonState.value == ButtonState.EXPAND
            ) {
                Text(
                    modifier = Modifier,
                    text = "Download",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
