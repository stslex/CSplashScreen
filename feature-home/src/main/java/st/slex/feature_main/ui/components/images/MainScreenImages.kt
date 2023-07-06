package st.slex.feature_main.ui.components.images

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.core.UtilsExtensions.convertedUrl
import st.slex.core_photos.ui.PhotoModel
import st.slex.feature_main.ui.components.base.MainScreenBaseItem
import st.slex.feature_main.ui.components.base.MainScreenBaseLazyList
import st.slex.feature_main.ui.components.base.MainUserAvatar
import st.slex.feature_main.ui.components.base.MainUsername
import st.slex.feature_main.ui.components.tabs.MainScreenTabs

@SuppressLint("RestrictedApi")
@Composable
fun MainScreenImages(
    items: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    MainScreenBaseLazyList(
        modifier = modifier,
        count = items.itemCount,
        key = items.itemKey { item ->
            item.uuid
        },
        contentType = {
            MainScreenTabs.PHOTOS
        }
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
    ) {
        MainUserAvatar(item.userUrl)
        Spacer(modifier = Modifier.width(16.dp))
        MainUsername(item.username)
    }
}
