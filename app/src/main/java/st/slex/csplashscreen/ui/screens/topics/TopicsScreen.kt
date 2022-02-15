package st.slex.csplashscreen.ui.screens.topics

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.google.accompanist.pager.ExperimentalPagerApi
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.ui.components.normalizedItemPosition
import st.slex.csplashscreen.ui.theme.Typography
import kotlin.math.absoluteValue


@ExperimentalAnimationApi
@SuppressLint("RememberReturnType", "RestrictedApi")
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TopicsScreen(
    state: LazyListState = rememberLazyListState(),
    viewModel: TopicsViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.topics.collectAsLazyPagingItems()
    LazyRow(
        state = state,
        content = topicsRaw(lazyPagingItems, state)
    )
}

private fun topicsRaw(
    lazyPagingItems: LazyPagingItems<TopicsModel>,
    state: LazyListState
): LazyListScope.() -> Unit = {
    items(lazyPagingItems, key = { it.id }) { item ->
        Column(
            modifier = Modifier
                .graphicsLayer(animateTopics(state, item?.id.toString()))
                .width(250.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            ImageTitleText(title = item?.title.toString())
            ItemImage(item?.cover_photo?.urls?.regular.toString())
        }
    }
}

@Composable
private fun ImageTitleText(title: String) {
    Text(
        text = title,
        style = Typography.h5,
        maxLines = 1
    )
}

private fun animateTopics(
    state: LazyListState,
    id: String
): GraphicsLayerScope.() -> Unit = {
    state.calculateItemPosition(id).let { value ->
        alpha = value
        scaleX = value
        scaleY = value
    }
}

private fun LazyListState.calculateItemPosition(id: String): Float =
    1 - (layoutInfo.normalizedItemPosition(id).absoluteValue * 0.15F)

@Composable
private fun ItemImage(url: String) {
    Surface(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(top = 32.dp)
    ) {
        GlideImage(
            imageModel = url,
            contentScale = ContentScale.FillBounds,
            circularReveal = CircularReveal(duration = 1000),
            requestBuilder = {
                Glide.with(LocalContext.current.applicationContext).asDrawable()
            }
        )
    }
}


