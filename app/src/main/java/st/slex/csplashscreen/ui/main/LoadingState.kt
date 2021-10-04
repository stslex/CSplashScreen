package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.android.material.animation.AnimationUtils

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ImageItemLoading(page: Int = 0, scope: PagerScope? = null) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)) {
            Surface(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    )
            ) {}
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                        color = Color.Gray
                    ),
                text = "User name placeholder"
            )
        }

        Surface(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                    color = Color.Gray
                )
                .graphicsLayer {
                    scope?.let {
                        val pageOffset = it.calculateCurrentOffsetForPage(page)
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

                }
                .aspectRatio(1f)
                .shadow(elevation = 8.dp, clip = true)
        ) {}
    }
}