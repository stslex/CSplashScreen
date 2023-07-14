package com.stslex.csplashscreen.feature.user.ui.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import com.stslex.csplashscreen.core.ui.utils.UiExt.toDp
import com.stslex.csplashscreen.feature.user.ui.utils.SwipeScrollConnection
import com.stslex.csplashscreen.feature.user.ui.utils.SwipeState

@OptIn(ExperimentalMaterialApi::class)
@Stable
data class UserSwipeState(
    val swipeableState: SwipeableState<SwipeState>,
    val swipeScrollConnection: SwipeScrollConnection
) {

    val offsetDp: Dp
        @Composable
        @ReadOnlyComposable
        get() = swipeableState.offset.value.toDp
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberUserSwipeState(
    isOnPreFlingAllow: () -> Boolean
): UserSwipeState {
    val swipeableState = rememberSwipeableState(
        initialValue = SwipeState.EXPAND
    )

    val swipeScrollConnection = remember {
        SwipeScrollConnection(
            swipeableState = swipeableState,
            isOnPreFlingAllow = isOnPreFlingAllow
        )
    }

    return remember {
        UserSwipeState(
            swipeableState = swipeableState,
            swipeScrollConnection = swipeScrollConnection
        )
    }
}