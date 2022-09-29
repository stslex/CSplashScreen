package st.slex.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_ui.theme.TransparentGray
import st.slex.core_ui.theme.Typography


@Composable
fun CollectionItem(
    item: CollectionModel,
    modifier: Modifier,
    isUserVisible: Boolean = true,
    onUserHeadClick: (username: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profile_image.medium,
                username = item.user.username,
                onProfileClick = onUserHeadClick
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        BindCoverImageCard(
            item.id,
            item.cover_photo.urls.regular,
            item.title,
            item.total_photos,
            onCollectionClick = onCollectionClick
        )
    }
}

@Composable
fun BindCoverImageCard(
    id: String,
    url: String,
    title: String,
    totalPhotos: Int,
    onCollectionClick: (id: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(elevation = 0.dp)
            .clickable {
                //            val destination = NavHostResource.CollectionScreen.destination
//            val route = "$destination/$id"
//            navController.navigate(route)
//            TODO
                onCollectionClick(id)
            },
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        CoverPhotoItem(url = url)
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = TransparentGray
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CollectionTextCard(text = title, Typography.titleMedium)
            Spacer(modifier = Modifier.padding(4.dp))
            CollectionTextCard(text = "$totalPhotos Photos", style = Typography.titleLarge)
        }
    }
}

