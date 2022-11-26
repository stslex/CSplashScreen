package st.slex.feature_main.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import st.slex.core_ui.components.CollectionItem
import st.slex.core_ui.components.ImageItem
import st.slex.core_ui.components.animatePager
import st.slex.core_ui.components.checkState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenPager(
    pagesResource: List<MainPagerTabResource<out Any>>,
    pagerState: PagerState,
    viewModel: MainScreenViewModel
) {
    HorizontalPager(
        count = pagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()
        val pagingResource = pagesResource[pageNumber]

        LazyColumn(state = listState) {
            when (pagingResource) {
                is MainPagerTabResource.Photos -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        val image = item ?: return@items
                        ImageItem(
                            modifier = Modifier.animatePager(
                                this@HorizontalPager, pageNumber, listState, item.id
                            ),
                            item = image,
                            onImageClick = viewModel::onImageClick,
                            onProfileClick = viewModel::onProfileClick
                        )
                    }
                }

                is MainPagerTabResource.Collections -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        val collection = item ?: return@items
                        CollectionItem(
                            modifier = Modifier.animatePager(
                                this@HorizontalPager, pageNumber, listState, collection.id
                            ),
                            item = collection,
                            onUserHeadClick = viewModel::onProfileClick,
                            onCollectionClick = {
                                viewModel.onCollectionClick(collection.id)
                            }
                        )
                    }
                }
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}