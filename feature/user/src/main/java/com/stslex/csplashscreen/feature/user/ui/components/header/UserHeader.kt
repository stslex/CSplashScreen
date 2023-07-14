package com.stslex.csplashscreen.feature.user.ui.components.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import st.slex.core_network.model.ui.user.UserModel

@Composable
fun UserHeader(
    user: UserModel?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        UserHeadComponent(
            totalPhotos = user?.totalPhotos ?: 0,
            totalLikes = user?.totalLikes ?: 0,
            totalCollections = user?.totalCollections ?: 0,
            url = user?.profileImageModel?.large.orEmpty()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        Spacer(modifier = Modifier.size(16.dp))
        val bio = user?.bio ?: "bio"
        if (bio.isNotEmpty()) {
            BindUserBio(bioText = bio)
            Spacer(modifier = Modifier.size(16.dp))
            Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        }
    }
}
