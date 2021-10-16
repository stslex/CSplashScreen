package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@SuppressLint("RestrictedApi")
fun Modifier.animationUtilPager(scope: PagerScope, page: Int): Modifier = this
    .graphicsLayer {
        val pageOffset = scope.calculateCurrentOffsetForPage(page).absoluteValue
        AnimationUtils
            .lerp(
                0.85f,
                1f,
                1f - pageOffset.coerceIn(0f, 1f)
            )
            .also { scale ->
                scaleX = scale
                scaleY = scale
            }
        alpha = AnimationUtils.lerp(
            0.5f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
        )
    }