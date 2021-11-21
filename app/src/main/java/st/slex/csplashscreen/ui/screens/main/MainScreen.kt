package st.slex.csplashscreen.ui.screens.main

import android.os.Parcelable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect

@FlowPreview
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    navController: NavController,
    pagerState: PagerState = rememberPagerState(),
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: MainScreenViewModel
) {
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
    LazyListColumn(
        navController = navController,
        pagesResource = viewModel.getListOfPagesResource()
    )
}

@FlowPreview
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
private fun LazyListColumn(
    navController: NavController,
    pagerState: PagerState = rememberPagerState(),
    pagesResource: List<MainPagerTabResource<out Parcelable>>,
) {
    Column {
        TabRow(pagerState = pagerState, listPagesResource = pagesResource)
        Pager(pagesResource = pagesResource, pagerState = pagerState, navController = navController)
    }
}

@Suppress("UNUSED_PARAMETER")
object AnalyticsService {
    fun sendPageSelectedEvent(page: Int) = Unit
}