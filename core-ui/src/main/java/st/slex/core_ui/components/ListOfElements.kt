package st.slex.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import st.slex.core_network.model.ui.collection.CollectionModel


@Composable
fun CollectionItem(
    item: CollectionModel,
    modifier: Modifier,
    isUserVisible: Boolean = true,
    onUserHeadClick: (username: String) -> Unit,
    onCollectionClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profileImageModel.medium,
                username = item.user.username,
                onProfileClick = onUserHeadClick
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        BindCoverImageCard(
            item.coverPhoto.urls.regular,
            item.title,
            item.totalPhotos,
            onCollectionClick = onCollectionClick
        )
    }
}

@Composable
fun BindCoverImageCard(
    url: String,
    title: String,
    totalPhotos: Int,
    onCollectionClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(elevation = 0.dp)
            .clickable(onClick = onCollectionClick),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        CoverPhotoItem(url = url)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CollectionTextCard(text = title, MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.padding(4.dp))
            CollectionTextCard(
                text = "$totalPhotos Photos",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

