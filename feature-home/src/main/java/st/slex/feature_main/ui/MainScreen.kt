package st.slex.feature_main.ui

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel,
    pagerState: PagerState = rememberPagerState()
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            AnalyticsService.sendPageSelectedEvent(page)
        }
    }
    val pagesResource = viewModel.listOfPagesResource()
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(pagerState = pagerState, listPagesResource = pagesResource)
        MainScreenPager(
            pagesResource = pagesResource,
            pagerState = pagerState,
            viewModel = viewModel
        )
    }
}

private val MainScreenViewModel.listOfPagesResource: @Composable () -> List<MainPagerTabResource<out Parcelable>>
    get() = {
        listOf(
            MainPagerTabResource.Photos(photos.collectAsLazyPagingItems()),
            MainPagerTabResource.Collections(collections.collectAsLazyPagingItems())
        )
    }


@Suppress("UNUSED_PARAMETER")
object AnalyticsService {
    //TODO
    fun sendPageSelectedEvent(page: Int) = Unit
}