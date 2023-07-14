package com.stslex.csplashscreen.feature.user.ui.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity

@OptIn(ExperimentalMaterialApi::class)
class SwipeScrollConnection(
    private val swipeableState: SwipeableState<SwipeState>,
    private val isOnPreFlingAllow: () -> Boolean,
) : NestedScrollConnection {

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        return if (delta < 0) {
            swipeableState.performDrag(delta).toOffset()
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        return swipeableState.performDrag(delta).toOffset()
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return if (available.y < 0 && isOnPreFlingAllow()) {
            swipeableState.performFling(available.y)
            available
        } else {
            Velocity.Zero
        }
    }

    override suspend fun onPostFling(
        consumed: Velocity,
        available: Velocity
    ): Velocity {
        swipeableState.performFling(velocity = available.y)
        return super.onPostFling(consumed, available)
    }

    private fun Float.toOffset() = Offset(0f, this)
}