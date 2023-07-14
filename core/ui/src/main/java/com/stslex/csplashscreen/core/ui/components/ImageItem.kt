package com.stslex.csplashscreen.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.core.UtilsExtensions.convertedUrl
import com.stslex.csplashscreen.core.network.model.ui.ImageModel

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    item: ImageModel,
    isUserVisible: Boolean = true,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profileImageModel.medium,
                username = item.user.username,
                onProfileClick = onProfileClick
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Surface(
            modifier = Modifier.clickable {
                val encodedUrl = item.urls.regular.convertedUrl
                onImageClick(encodedUrl, item.uuid)
            },
            content = {
                CoverPhotoItem(url = item.urls.regular)
            }
        )
    }
}