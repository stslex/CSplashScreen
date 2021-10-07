package st.slex.csplashscreen.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavBackStackEntry
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.animation.AnimationUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.ui.navigation.NavigationState
import st.slex.csplashscreen.ui.theme.Typography
import st.slex.csplashscreen.utiles.GET_COLLECTIONS
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
interface MainScreen {

    @Composable
    fun BindScreen(args: NavBackStackEntry, viewModel: MainScreenViewModel)

    class Base @Inject constructor() : MainScreen {

        @Composable
        override fun BindScreen(args: NavBackStackEntry, viewModel: MainScreenViewModel) {
            viewModel.setQueryCollections(listOf(GET_COLLECTIONS))
            viewModel.setQueryPhotos(QueryPhotos.AllPhotos)

            val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
            val lazyPagingCollectionsItems = viewModel.collections.collectAsLazyPagingItems()

            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }

            MainScreenPager(
                lazyPagingPhotosItems,
                lazyPagingCollectionsItems,
                viewModel
            )
        }

        @Composable
        private fun MainScreenPager(
            lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
            lazyPagingCollectionsItems: LazyPagingItems<CollectionModel>,
            viewModel: MainScreenViewModel
        ) {
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    AnalyticsService.sendPageSelectedEvent(page)
                }
            }

            val pages = listOf(PagerMainTab.Photos, PagerMainTab.Collections)

            Scaffold(
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    MainScreenFloatingActionButton {
                        viewModel.navigate(NavigationState.SearchPhotosScreen, listOf(" "))
                    }
                }
            ) {
                Column {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                            )
                        },
                        tabs = {
                            pages.forEachIndexed { index, model ->
                                Tab(
                                    text = {
                                        Text(
                                            text = model.title,
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

                    HorizontalPager(
                        count = pages.size,
                        state = pagerState
                    ) { page ->
                        LazyColumn {
                            when (pages[page]) {
                                is PagerMainTab.Photos -> {
                                    items(lazyPagingPhotosItems) { item ->
                                        ImageItem(
                                            item = item,
                                            modifier = Modifier.animationUtilPager(
                                                scope = this@HorizontalPager,
                                                page = page
                                            )
                                        ) { destination, args ->
                                            viewModel.navigate(destination, args)
                                        }
                                    }
                                    lazyPagingPhotosItems.checkState(this)
                                }

                                is PagerMainTab.Collections -> {
                                    items(lazyPagingCollectionsItems) { item ->
                                        CollectionItem(
                                            item = item,
                                            modifier = Modifier.animationUtilPager(
                                                scope = this@HorizontalPager,
                                                page = page
                                            ),
                                            navigation = { destination, args ->
                                                viewModel.navigate(destination, args)
                                            }
                                        )

                                    }
                                    lazyPagingCollectionsItems.checkState(this)
                                }
                            }
                        }
                    }
                }
            }

        }

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

        @Composable
        private inline fun MainScreenFloatingActionButton(crossinline onClick: () -> Unit) {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "search photos",
                        textAlign = TextAlign.Center,
                    )
                },
                onClick = { onClick() }
            )
        }

        @Suppress("UNUSED_PARAMETER")
        object AnalyticsService {
            fun sendPageSelectedEvent(page: Int) = Unit
        }

    }
}






