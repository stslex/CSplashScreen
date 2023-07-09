package st.slex.feature_main.ui.components.images

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.core.UtilsExtensions.convertedUrl
import com.stslex.csplashscreen.core.ui.theme.Dimen
import st.slex.core_photos.ui.PhotoModel
import st.slex.feature_main.ui.components.base.MainScreenBaseItem
import st.slex.feature_main.ui.components.base.MainScreenBaseLazyList
import st.slex.feature_main.ui.components.base.MainUserAvatar
import st.slex.feature_main.ui.components.base.MediumText
import st.slex.feature_main.ui.components.tabs.MainScreenTabs

@Composable
fun MainScreenImages(
    items: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
) {
    MainScreenBaseLazyList(
        modifier = modifier,
        count = items.itemCount,
        key = items.itemKey { item ->
            item.uuid
        },
        contentType = {
            MainScreenTabs.PHOTOS
        },
        listState = listState
    ) { index ->
        items[index]?.let { item ->
            MainScreenImageItem(
                item = item,
                onImageClick = onImageClick,
                onUserClick = onUserClick,
            )
        }
    }
}

@Composable
fun MainScreenImageItem(
    item: PhotoModel,
    onUserClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    modifier: Modifier = Modifier,
) {

    MainScreenBaseItem(
        modifier = modifier,
        onContainerClick = remember(item.uuid) {
            { onImageClick(item.url.convertedUrl, item.uuid) }
        },
        onHeaderClick = remember(item.username) {
            { onUserClick(item.username) }
        },
        url = item.url,
        headerContent = {
            MainUserAvatar(item.userUrl)
            Spacer(modifier = Modifier.width(Dimen.medium))
            MediumText(item.username)
        }
    )
}
