package com.stslex.csplashscreen.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

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