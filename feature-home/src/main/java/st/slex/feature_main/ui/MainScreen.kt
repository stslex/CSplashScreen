package st.slex.feature_main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
    val pagesResource = viewModel.listOfPagesResource()
    Column(
        modifier = modifier
    ) {
        TabRow(pagerState = pagerState, listPagesResource = pagesResource)
        MainScreenPager(
            pagesResource = pagesResource,
            pagerState = pagerState,
            viewModel = viewModel
        )
    }
}

private val MainScreenViewModel.listOfPagesResource: @Composable () -> List<MainPagerTabResource<out Any>>
    get() = {
        listOf(
            MainPagerTabResource.Photos(photos.collectAsLazyPagingItems()),
            MainPagerTabResource.Collections(collections.collectAsLazyPagingItems())
        )
    }