package com.stslex.csplashscreen.core.ui.utils

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.tabIndicatorOffset(
    currentTabPosition: TabPosition,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val tabWidth = currentTabPosition.width / 2
    val leftOffset = (currentTabPosition.width - tabWidth) / 2

    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "currentTabWidth"
    )

    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "indicatorOffset"
    )

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(
            x = indicatorOffset + leftOffset
        )
        .width(currentTabWidth)
}
