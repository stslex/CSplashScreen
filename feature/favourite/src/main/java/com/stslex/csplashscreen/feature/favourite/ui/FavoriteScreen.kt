package com.stslex.csplashscreen.feature.favourite.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.base.PagingDataBox
import com.stslex.csplashscreen.core.ui.theme.Dimen

@Composable
fun FavouriteScreen(
    photos: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (uuid: String) -> Unit,
    onGoToPhotosClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PagingDataBox(
        modifier = modifier,
        items = photos,
        error = { error ->
            Text(
                text = error.localizedMessage.orEmpty(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        loading = {
            CircularProgressIndicator()
        },
        emptyContent = {
            EmptyContent(onGoToPhotosClick)
        }
    ) {
        LazyListPhotos(
            items = photos,
            onUserClick = onUserClick,
            onImageClick = onImageClick
        )
    }
}

@Composable
private fun EmptyContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier
            .padding(Dimen.large)
    ) {
        Column(
            modifier = Modifier.padding(Dimen.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add photos to favourites to see them here",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Dimen.medium))

            OutlinedButton(onClick = onClick) {
                Text(
                    text = "Go to Photos",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
