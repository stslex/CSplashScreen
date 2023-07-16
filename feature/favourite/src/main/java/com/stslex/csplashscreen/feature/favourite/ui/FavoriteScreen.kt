package com.stslex.csplashscreen.feature.favourite.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel

@Composable
fun FavouriteScreen(
    photos: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (uuid: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyListPhotos(
        modifier = modifier,
        items = photos,
        onUserClick = onUserClick,
        onImageClick = onImageClick
    )
}