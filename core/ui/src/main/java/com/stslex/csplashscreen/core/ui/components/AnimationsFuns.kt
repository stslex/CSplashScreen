package com.stslex.csplashscreen.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.google.android.material.animation.AnimationUtils
import kotlin.math.absoluteValue

fun Modifier.animatePager(
    currentPageOffset: Float,
    lazyListState: LazyListState,
    id: String
): Modifier = animatePager(currentPageOffset)
    .setScrollingColumnAnimation(lazyListState, id)

@SuppressLint("RestrictedApi")
fun Modifier.animatePager(
    currentPageOffset: Float
): Modifier = this.graphicsLayer {
    AnimationUtils.lerp(
        0.85f,
        1f,
        1f - currentPageOffset.coerceIn(0f, 1f)
    ).also { scale ->
        scaleX = scale
        scaleY = scale
    }
    alpha = AnimationUtils.lerp(
        0.5f,
        1f,
        1f - currentPageOffset.coerceIn(0f, 1f)
    )
}

fun Modifier.setScrollingColumnAnimation(
    lazyListState: LazyListState,
    id: String?
): Modifier = graphicsLayer {
    val value = 1 - (lazyListState.normalizedPosition(id).absoluteValue * 0.05f)
    alpha = value
    scaleX = value
    scaleY = value
}

fun LazyListState.normalizedPosition(key: String?): Float = with(layoutInfo) {
    visibleItemsInfo
        .firstOrNull {
            it.key == key
        }
        ?.let { itemInfo ->
            val center = (viewportEndOffset + viewportStartOffset - itemInfo.size) / 2F
            (itemInfo.offset.toFloat() - center) / center
        }
        ?: 0F
}

fun Modifier.animateItemTop(
    listState: LazyListState,
    key: Any?,
    valueKf: Float = 0.05f
): Modifier = graphicsLayer {
    val position = listState.normalizedPositionTop(key)
    val value = 1 - (position.absoluteValue * valueKf)
    alpha = value
    scaleX = value
    scaleY = value
}

fun LazyListState.normalizedPositionTop(
    key: Any?
): Float = with(layoutInfo) {
    visibleItemsInfo.firstOrNull {
        it.key == key
    }?.let {
        1 - (it.size - it.offset.toFloat()) / it.size
    } ?: 0F
}