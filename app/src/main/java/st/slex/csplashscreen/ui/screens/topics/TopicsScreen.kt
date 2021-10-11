package st.slex.csplashscreen.ui.screens.topics

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.*
import com.google.android.material.animation.AnimationUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.theme.Typography


@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TopicsScreen(
    navController: NavController,
    pagerState: PagerState = rememberPagerState(),
    viewModel: TopicsViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val lazyPagingItems = viewModel.topics.collectAsLazyPagingItems()

    HorizontalPager(
        count = lazyPagingItems.itemCount,
        state = pagerState,
        contentPadding = PaddingValues(start = 70.dp, end = 32.dp)
    ) { page ->

        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .animationUtilPager(
                    scope = this@HorizontalPager,
                    page = page
                )
        ) {
            Text(
                text = lazyPagingItems[page]?.title.toString(),
                style = Typography.h5,
                maxLines = 1
            )
            Surface(modifier = Modifier.padding(top = 32.dp)) {
                Image(
                    modifier = Modifier
                        .clipToBounds(),
                    painter = rememberImagePainter(data = lazyPagingItems[page]?.cover_photo?.urls?.regular.toString()) {
                        this.crossfade(500)
                        this.transformations(RoundedCornersTransformation())
                    },
                    contentDescription = ""
                )
            }
        }

    }
}

@ExperimentalPagerApi
@SuppressLint("RestrictedApi")
private fun Modifier.animationUtilPager(scope: PagerScope, page: Int): Modifier = this
    .graphicsLayer {
        val pageOffset = scope.calculateCurrentOffsetForPage(page)
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
    .aspectRatio(1f)