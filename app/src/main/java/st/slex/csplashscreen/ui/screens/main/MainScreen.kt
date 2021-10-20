package st.slex.csplashscreen.ui.screens.main

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.animation.AnimationUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.core.QueryCollections
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.CollectionItem
import st.slex.csplashscreen.ui.components.ImageItem
import st.slex.csplashscreen.ui.components.checkState
import st.slex.csplashscreen.ui.components.normalizedItemPosition
import st.slex.csplashscreen.ui.navigation.Navigator
import st.slex.csplashscreen.ui.theme.Typography
import kotlin.math.absoluteValue

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    pagerState: PagerState = rememberPagerState(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: MainScreenViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val navigator = viewModel.navigator

    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    Column {
        Pager(navigator = navigator, listPagesResource = viewModel.getListOfPagesResource())
    }
}

@Composable
@ExperimentalCoroutinesApi
private fun MainScreenViewModel.getListOfPagesResource(): List<MainPagerTabResource<out Parcelable>> =
    listOf(
        MainPagerTabResource.Photos(photos.collectAsLazyPagingItems()),
        MainPagerTabResource.Collections(collections.collectAsLazyPagingItems())
    )

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun Pager(
    navigator: Navigator,
    pagerState: PagerState = rememberPagerState(),
    listPagesResource: List<MainPagerTabResource<out Parcelable>>,
) {
    TabRow(pagerState = pagerState, listPagesResource = listPagesResource)
    HorizontalPager(
        count = listPagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()
        val pagingResource = listPagesResource[pageNumber]

        @Composable
        fun Parcelable.SetItemDependsOfType(id: String) {
            val animateModifier: Modifier =
                Modifier.animateColumn(this@HorizontalPager, pageNumber, listState, id)
            SetCurrentItem(navigator = navigator, modifier = animateModifier)
        }

        LazyColumn(state = listState) {
            when (pagingResource) {
                is MainPagerTabResource.Photos -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }

                is MainPagerTabResource.Collections -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Parcelable.SetCurrentItem(
    navigator: Navigator,
    modifier: Modifier
) {
    if (this is ImageModel) {
        ImageItem(item = this, modifier = modifier, navigator = navigator)
    } else if (this is CollectionModel) {
        CollectionItem(item = this, modifier = modifier, navigator = navigator)
    }
}

@ExperimentalPagerApi
@Composable
private fun TabRow(
    pagerState: PagerState,
    listPagesResource: List<MainPagerTabResource<out Parcelable>>
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        tabs = {
            listPagesResource.forEachIndexed { index, page ->
                Tab(
                    text = {
                        Text(
                            text = page.title,
                            style = Typography.subtitle1
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
    )
}

@Suppress("UNUSED_PARAMETER")
object AnalyticsService {
    fun sendPageSelectedEvent(page: Int) = Unit
}

@ExperimentalPagerApi
@SuppressLint("RestrictedApi")
fun Modifier.animateColumn(
    scope: PagerScope,
    page: Int,
    lazyListState: LazyListState,
    id: String
): Modifier = this
    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
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
        translationY = lazyListState.layoutInfo.normalizedItemPosition(id) * -50
        alpha = AnimationUtils.lerp(
            0.5f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
        )
    }
    .graphicsLayer {
        val value =
            1 - (lazyListState.layoutInfo.normalizedItemPosition(id).absoluteValue * 0.05f)
        alpha = value
        scaleX = value
        scaleY = value
    }







