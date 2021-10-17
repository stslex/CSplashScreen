package st.slex.csplashscreen.ui.screens.topics

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.normalizedItemPosition
import st.slex.csplashscreen.ui.theme.Typography
import kotlin.math.absoluteValue


@ExperimentalAnimationApi
@SuppressLint("RememberReturnType", "RestrictedApi")
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TopicsScreen(
    navController: NavController,
    viewModel: TopicsViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val lazyPagingItems = viewModel.topics.collectAsLazyPagingItems()
    val state: LazyListState = rememberLazyListState()
    LazyRow(state = state) {
        items(lazyPagingItems, key = { it.id }) { item ->
            Column(
                modifier = Modifier
                    .graphicsLayer {
                        val value =
                            1 - (state.layoutInfo.normalizedItemPosition(item?.id!!).absoluteValue * 0.15F)
                        alpha = value
                        scaleX = value
                        scaleY = value
                    }
                    .width(250.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item?.title.toString(),
                    style = Typography.h5,
                    maxLines = 1
                )
                ItemImage(
                    item?.cover_photo?.urls?.regular.toString(),
                )
            }
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


