package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@SuppressLint("RestrictedApi")
fun Modifier.animatePager(
    scope: PagerScope,
    page: Int,
    lazyListState: LazyListState,
    id: String
): Modifier = animatePager(scope, page).setScrollingColumnAnimation(lazyListState, id)

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
fun Modifier.animatePager(scope: PagerScope, page: Int): Modifier = this.graphicsLayer {
    val pageOffset = scope.calculateCurrentOffsetForPage(page).absoluteValue
    AnimationUtils.lerp(
        0.85f,
        1f,
        1f - pageOffset.coerceIn(0f, 1f)
    ).also { scale ->
        scaleX = scale
        scaleY = scale
    }
    alpha = AnimationUtils.lerp(
        0.5f,
        1f,
        1f - pageOffset.coerceIn(0f, 1f)
    )
}

fun LazyListState.normalizedPosition(key: String?): Float = with(layoutInfo) {
    visibleItemsInfo.firstOrNull {
        it.key == key
    }?.let {
        val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
        (it.offset.toFloat() - center) / center
    } ?: 0F
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