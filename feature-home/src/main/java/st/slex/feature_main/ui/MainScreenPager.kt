package st.slex.feature_main.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.core_ui.components.CollectionItem
import st.slex.core_ui.components.ImageItem
import st.slex.core_ui.components.animatePager
import st.slex.core_ui.components.checkState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenPager(
    pagesResource: List<MainPagerTabResource<out UIItemTypes>>,
    pagerState: PagerState,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
) {
    HorizontalPager(
        state = pagerState,
        key = null
    ) { pageNumber ->
        val listState: LazyListState = rememberLazyListState()

        val pagingResource = pagesResource[pageNumber]
        LazyColumn(state = listState) {

            items(
                count = pagingResource.pagingItems.itemCount,
                key = pagingResource.pagingItems.itemKey { item ->
                    item.itemId
                },
                contentType = pagingResource.pagingItems.itemContentType {
                    "image"
                }
            ) { index ->
                val item = pagingResource.pagingItems[index] ?: return@items

                val animateModifier: Modifier = Modifier.animatePager(
                    currentPageOffset = pagerState.currentPageOffsetFraction,
                    lazyListState = listState,
                    id = item.itemId
                )
                SetCurrentItem(
                    modifier = animateModifier,
                    item = item,
                    onUserHeadClick = onProfileClick,
                    onImageClick = onImageClick,
                    onCollectionClick = onCollectionClick
                )
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@Composable
private fun SetCurrentItem(
    modifier: Modifier = Modifier,
    item: UIItemTypes,
    onUserHeadClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    when (item) {
        is ImageModel -> ImageItem(
            modifier = modifier,
            item = item,
            onImageClick = onImageClick,
            onProfileClick = onUserHeadClick
        )

        is CollectionModel -> CollectionItem(
            modifier = modifier,
            item = item,
            onUserHeadClick = onUserHeadClick,
            onCollectionClick = onCollectionClick
        )
    }
}