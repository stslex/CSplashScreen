package st.slex.feature_main.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.feature_main.ui.MainPagerTabResource
import st.slex.feature_main.ui.MainScreenPager
import st.slex.feature_main.ui.components.tabs.MainScreenTabRow


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel,
    pagerState: PagerState = rememberPagerState()
) {
    val pagesResource = viewModel.listOfPagesResource()
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
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