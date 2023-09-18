package st.slex.csplashscreen.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object UiExt {

    val Dp.toPx: Float
        @Composable
        @ReadOnlyComposable
        get() = with(LocalDensity.current) {
            toPx()
        }

    val Float.toDp: Dp
        @Composable
        @ReadOnlyComposable
        get() = with(LocalDensity.current) {
            toDp()
        }

    val Int.toDp: Dp
        @Composable
        @ReadOnlyComposable
        get() = with(LocalDensity.current) {
            toDp()
        }
}

@Composable
fun <T> SharedFlow<T>.CollectAsEvent(
    minActionState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        this@CollectAsEvent
            .flowWithLifecycle(
                lifecycle = lifecycleOwner.lifecycle,
                minActiveState = minActionState
            )
            .onEach(action)
            .launchIn(lifecycleOwner.lifecycleScope)
    }
}