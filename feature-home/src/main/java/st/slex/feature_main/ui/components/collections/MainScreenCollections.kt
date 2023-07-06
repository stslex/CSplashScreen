package st.slex.feature_main.ui.components.collections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import st.slex.feature_main.ui.components.base.MainScreenBaseItem
import st.slex.feature_main.ui.components.base.MainScreenBaseLazyList
import st.slex.feature_main.ui.components.base.MainUserAvatar
import st.slex.feature_main.ui.components.base.MainUsername
import st.slex.feature_main.ui.components.tabs.MainScreenTabs

@Composable
fun MainScreenCollections(
    items: LazyPagingItems<CollectionModel>,
    onProfileClick: (username: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    MainScreenBaseLazyList(
        modifier = modifier,
        count = items.itemCount,
        key = items.itemKey { item ->
            item.uuid
        },
        contentType = {
            MainScreenTabs.COLLECTIONS
        },
    ) { index ->
        items[index]?.let { item ->
            MainScreenCollectionItem(
                item = item,
                onCollectionClick = onCollectionClick,
                onProfileClick = onProfileClick,
            )
        }
    }
}

@Composable
fun MainScreenCollectionItem(
    item: CollectionModel,
    onProfileClick: (username: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MainScreenBaseItem(
        modifier = modifier,
        onContainerClick = remember(item.uuid) {
            { onCollectionClick(item.uuid) }
        },
        onHeaderClick = remember(item.username) {
            { onProfileClick(item.username) }
        },
        url = item.url,
    ) {
        MainUserAvatar(item.userUrl)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            MainUsername(item.username)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier,
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                lineHeight = TextUnit.Unspecified
            )
        }
    }
}
