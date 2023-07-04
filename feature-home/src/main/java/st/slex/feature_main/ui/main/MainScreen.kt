package st.slex.feature_main.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.collections.immutable.toImmutableList
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
    val pagesResource = viewModel.rememberPagesResource()
    val pagerState = rememberPagerState {
        pagesResource.size
    }
    Column(
        modifier = modifier
    ) {
        MainScreenTabRow(
            modifier = Modifier,
            pagerState = pagerState,
            listPagesResource = pagesResource
        )
        MainScreenPager(
            pagesResource = pagesResource.toImmutableList(),
            pagerState = pagerState,
            onProfileClick = viewModel::onProfileClick,
            onImageClick = viewModel::onImageClick,
            onCollectionClick = viewModel::onCollectionClick
        )
    }
}

@Composable
private fun MainScreenViewModel.rememberPagesResource(
): List<MainPagerTabResource<out UIItemTypes>> = listOf(
    MainPagerTabResource.Photos(
        items = remember {
            photos
        }.collectAsLazyPagingItems()
    ),
    MainPagerTabResource.Collections(
        items = remember {
            collections
        }.collectAsLazyPagingItems()
    )
)