package st.slex.feature_main.ui

import android.os.Parcelable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_ui.components.CollectionItem
import st.slex.core_ui.components.ImageItem
import st.slex.core_ui.components.animatePager
import st.slex.core_ui.components.checkState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenPager(
    pagesResource: List<MainPagerTabResource<out Parcelable>>,
    pagerState: PagerState,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    HorizontalPager(
        count = pagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()
        val pagingResource = pagesResource[pageNumber]

        @Composable
        fun Parcelable.SetItemDependsOfType(id: String) {
            val animateModifier: Modifier = Modifier.animatePager(
                this@HorizontalPager, pageNumber, listState, id
            )
            SetCurrentItem(
                modifier = animateModifier,
                onUserHeadClick = onProfileClick,
                onImageClick = onImageClick,
                onCollectionClick = onCollectionClick
            )
        }

        LazyColumn(state = listState) {
            when (pagingResource) {
                is MainPagerTabResource.Photos -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }

                is MainPagerTabResource.Collections -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@Composable
private fun Parcelable.SetCurrentItem(
    modifier: Modifier = Modifier,
    onUserHeadClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    if (this is ImageModel) {
        ImageItem(
            modifier = modifier,
            item = this,
            onImageClick = onImageClick,
            onProfileClick = onUserHeadClick
        )
    } else if (this is CollectionModel) {
        CollectionItem(
            modifier = modifier,
            item = this,
            onUserHeadClick = onUserHeadClick,
            onCollectionClick = onCollectionClick
        )
    }
}