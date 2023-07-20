package com.stslex.csplashscreen.feature.user.ui.components.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.network.model.ui.user.UserModel
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTab

@Composable
fun UserHeader(
    user: UserModel?,
    lazyListState: LazyListState,
    onTabClick: (UserTab) -> Unit,
    modifier: Modifier = Modifier,
    itemsCount: Int = 10,
) {
    LazyColumn(
        modifier,
        state = lazyListState,
    ) {
        items(
            itemsCount,
            key = {
                it
            }
        ) {
            Column(
                modifier = Modifier
            ) {
                UserHeadComponent(
                    totalPhotos = user?.totalPhotos ?: 0,
                    totalLikes = user?.totalLikes ?: 0,
                    totalCollections = user?.totalCollections ?: 0,
                    url = user?.profileImageModel?.large.orEmpty(),
                    onTabClick = onTabClick
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
    }
}
