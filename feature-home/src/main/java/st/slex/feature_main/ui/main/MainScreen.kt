package st.slex.feature_main.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.feature_main.ui.MainPagerTabResource
import st.slex.feature_main.ui.MainScreenPager
import st.slex.feature_main.ui.components.tabs.MainScreenTabRow


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel,
) {
    val pagesResource = viewModel.listOfPagesResource()
    val pagerState = rememberPagerState { pagesResource.size }
    Column(
        modifier = modifier
    ) {
        MainScreenTabRow(
            modifier = Modifier,
            pagerState = pagerState,
            listPagesResource = pagesResource
        )
        MainScreenPager(
            pagesResource = pagesResource,
            pagerState = pagerState,
            onProfileClick = viewModel::onProfileClick,
            onImageClick = viewModel::onImageClick,
            onCollectionClick = viewModel::onCollectionClick
        )
    }
}

private val MainScreenViewModel.listOfPagesResource: @Composable () -> List<MainPagerTabResource<out UIItemTypes>>
    get() = {
        listOf(
            MainPagerTabResource.Photos(photos.collectAsLazyPagingItems()),
            MainPagerTabResource.Collections(collections.collectAsLazyPagingItems())
        )
    }