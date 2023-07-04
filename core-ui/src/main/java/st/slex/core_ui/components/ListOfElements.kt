package st.slex.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColorInt
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_ui.theme.lighten


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
                url = item.user.profileImageModel.medium,
                username = item.user.username,
                onProfileClick = onUserHeadClick,
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        CollectionItemComponent(
            url = item.coverPhoto.urls.regular,
            title = item.title,
            totalPhotos = item.totalPhotos,
            id = item.id,
            onCollectionClick = onCollectionClick,
            color = item.coverPhoto.color
        )
    }
}

@Composable
fun CollectionItemComponent(
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    totalPhotos: Int,
    id: String,
    onCollectionClick: (id: String) -> Unit,
    color: String
) {
    val textColor = Color(color.toColorInt())
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(elevation = 0.dp)
            .clickable(onClick = {
                onCollectionClick(id)
            })
    ) {
        val (coverImage, titles, blur) = createRefs()
        CoverPhotoItem(
            modifier = Modifier.constrainAs(coverImage) {
                width = Dimension.matchParent
                height = Dimension.matchParent
            },
            url = url
        )
        CoverBlurItem(
            modifier = Modifier
                .constrainAs(blur) {
                    width = Dimension.fillToConstraints
                    start.linkTo(coverImage.start)
                    end.linkTo(coverImage.end)
                    bottom.linkTo(coverImage.bottom)
                }
                .fillMaxWidth()
                .height(100.dp)
                .blur(50.dp),
            url = url
        )
        Column(
            modifier = Modifier
                .constrainAs(titles) {
                    width = Dimension.fillToConstraints
                    start.linkTo(blur.start)
                    end.linkTo(blur.end)
                    bottom.linkTo(blur.bottom)
                    top.linkTo(blur.top)
                }
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Bottom

        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                color = textColor.lighten(0.4f),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "$totalPhotos Photos",
                textAlign = TextAlign.Start,
                color = textColor.lighten(0.4f),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        }
    }

}
