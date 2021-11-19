package st.slex.csplashscreen.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import st.slex.csplashscreen.ui.components.normalizedItemPosition
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@SuppressLint("RestrictedApi")
fun Modifier.animateColumn(
    scope: PagerScope,
    page: Int,
    lazyListState: LazyListState,
    id: String
): Modifier = this
    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
    .pagerGraphicLayer(scope, page, lazyListState, id)
    .lazyListGraphicLazy(lazyListState, id)

private fun Modifier.lazyListGraphicLazy(
    lazyListState: LazyListState,
    id: String
) = graphicsLayer {
    val value = lazyListState.calculateValue(id)
    alpha = value
    scaleX = value
    scaleY = value
}

private fun LazyListState.calculateValue(id: String) =
    1 - (layoutInfo.normalizedItemPosition(id).absoluteValue * 0.05f)


@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
private fun Modifier.pagerGraphicLayer(
    scope: PagerScope,
    page: Int,
    lazyListState: LazyListState,
    id: String
) = graphicsLayer {
    val pageOffset = scope.calculateCurrentOffsetForPage(page).absoluteValue
    AnimationUtils
        .lerp(0.85f, 1f, pageOffset.calcPageOffset)
        .also { scale ->
            scaleX = scale
            scaleY = scale
        }
    translationY = lazyListState.layoutInfo.normalizedItemPosition(id) * -50
    alpha = AnimationUtils.lerp(0.5f, 1f, pageOffset.calcPageOffset)
}

private val Float.calcPageOffset: Float
    get() = 1f - coerceIn(0f, 1f)