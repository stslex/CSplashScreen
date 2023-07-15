package com.stslex.csplashscreen.core.photos.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.base.MainUserAvatar
import com.stslex.csplashscreen.core.ui.components.base.MediumText
import com.stslex.csplashscreen.core.ui.components.base.PhotosBaseItem
import com.stslex.csplashscreen.core.ui.theme.Dimen

@Composable
fun LazyListPhotoItem(
    item: PhotoModel,
    onUserClick: (username: String) -> Unit,
    onImageClick: (imageId: String) -> Unit,
    modifier: Modifier = Modifier,
) {

    PhotosBaseItem(
        modifier = modifier,
        onContainerClick = remember(item.uuid) {
            { onImageClick(item.uuid) }
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
