package st.slex.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import st.slex.core.UtilsExtensions.convertUrl
import st.slex.core_network.model.ui.image.ImageModel


@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    item: ImageModel?,
    isUserVisible: Boolean = true,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item?.user?.profile_image?.medium.toString(),
                username = item?.user?.username.toString(),
                onProfileClick = onProfileClick
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Surface(
            modifier = Modifier.clickable {
                val id = item?.id ?: return@clickable
                val encodedUrl = item.urls.regular.convertUrl()
                onImageClick(encodedUrl, id)
            },
            content = { CoverPhotoItem(url = item?.urls?.regular.toString()) }
        )
    }
}