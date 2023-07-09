package st.slex.feature_main.ui.components.collections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.ui.theme.Dimen
import st.slex.feature_main.ui.components.base.MainScreenBaseItem
import st.slex.feature_main.ui.components.base.MainScreenBaseLazyList
import st.slex.feature_main.ui.components.base.MainUserAvatar
import st.slex.feature_main.ui.components.base.MediumText
import st.slex.feature_main.ui.components.base.SmallText
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
        headerContent = {
            MainUserAvatar(item.userUrl)
            Spacer(modifier = Modifier.width(Dimen.medium))
            Column {
                MediumText(item.username)

            }
        }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.background.copy(
                        alpha = 0.7f
                    )
                )
                .padding(Dimen.small)
        ) {
            MediumText(item.title)
            Spacer(modifier = Modifier.height(Dimen.small))
            SmallText("Photos: ${item.totalPhotos}")
        }
    }
}
