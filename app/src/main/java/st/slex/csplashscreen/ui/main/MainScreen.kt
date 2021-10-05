package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.MainViewModel
import st.slex.csplashscreen.ui.components.ImageItemLoading
import st.slex.csplashscreen.ui.theme.Typography
import st.slex.csplashscreen.utiles.GET_COLLECTIONS
import st.slex.csplashscreen.utiles.GET_PHOTOS

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoroutinesApi
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val queryPhotos = listOf(GET_PHOTOS)
    val queryCollections = listOf(GET_COLLECTIONS)

    viewModel.setQueryPhotos(queryPhotos)
    viewModel.setQueryCollections(queryCollections)

    val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
    val lazyPagingCollectionsItems = viewModel.collections.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    val isSystemInDarkTheme = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }

    Pager(lazyPagingPhotosItems, lazyPagingCollectionsItems, navController)
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun Pager(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    lazyPagingCollectionsItems: LazyPagingItems<CollectionModel>,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    val pages = listOf("Photos", "Collections")

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            MainScreenFloatingActionButton {
                //navController.navigate()
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
                divider = {

                },
                tabs = {
                    pages.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    text = title,
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
                    when (page) {
                        pages.indexOf("Photos") -> {
                            items(lazyPagingPhotosItems) { item ->
                                ImageItem(item, navController, page, this@HorizontalPager)
                            }
                            lazyPagingPhotosItems.checkState { loadState() }
                        }
                        pages.indexOf("Collections") -> {
                            items(lazyPagingCollectionsItems) { item ->
                                CollectionItem(item, navController, page, this@HorizontalPager)
                            }
                            lazyPagingCollectionsItems.checkState { loadState() }
                        }
                    }

                }
            }


        }


    }

}

@Composable
inline fun MainScreenFloatingActionButton(crossinline onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = "Open all tags",
                textAlign = TextAlign.Center,
            )
        },
        onClick = { onClick() }
    )
}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
fun LazyListScope.loadState() = repeat(3) {
    item { ImageItemLoading() }
}


inline fun <T : Any> LazyPagingItems<T>.checkState(crossinline function: () -> Unit) {
    when {

    }
}

@Suppress("UNUSED_PARAMETER")
object AnalyticsService {
    fun sendPageSelectedEvent(page: Int) = Unit
}



