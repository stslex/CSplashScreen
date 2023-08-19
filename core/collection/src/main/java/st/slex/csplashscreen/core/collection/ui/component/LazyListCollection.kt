package st.slex.csplashscreen.core.collection.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.ui.components.base.PhotosBaseLazyList

@Composable
fun LazyListCollection(
    items: LazyPagingItems<CollectionModel>,
    onProfileClick: (username: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentType: Any? = null,
) {
    PhotosBaseLazyList(
        modifier = modifier,
        items = items,
        key = { item ->
            item.uuid
        },
        contentType = { contentType ?: "collections" },
        listState = listState
    ) { item ->
        LazyListCollectionItem(
            item = item,
            onCollectionClick = onCollectionClick,
            onProfileClick = onProfileClick,
        )
    }
}
