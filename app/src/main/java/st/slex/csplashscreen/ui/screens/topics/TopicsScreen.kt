package st.slex.csplashscreen.ui.screens.topics

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.animationUtilPager
import st.slex.csplashscreen.ui.theme.Typography


@ExperimentalAnimationApi
@SuppressLint("RememberReturnType", "RestrictedApi")
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
    val paddingValues = PaddingValues(start = 32.dp, end = 32.dp, top = 128.dp, bottom = 128.dp)
    HorizontalPager(
        count = lazyPagingItems.itemCount,
        state = pagerState,
        contentPadding = paddingValues
    ) { horizontalPage ->
        Column(
            modifier = Modifier
                .animationUtilPager(
                    scope = this@HorizontalPager,
                    page = horizontalPage
                )
        ) {
            Text(
                text = lazyPagingItems[horizontalPage]?.title.toString(),
                style = Typography.h5,
                maxLines = 1
            )
            ItemImage(
                lazyPagingItems[horizontalPage]?.cover_photo?.urls?.regular.toString(),
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun ItemImage(url: String) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(top = 32.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = url) {
                crossfade(500)
                transformations(RoundedCornersTransformation())
            },
            contentDescription = "",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}
