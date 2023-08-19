package st.slex.csplashscreen.core.collection.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.ui.components.base.MainUserAvatar
import st.slex.csplashscreen.core.ui.components.base.MediumText
import st.slex.csplashscreen.core.ui.components.base.PhotosBaseItem
import st.slex.csplashscreen.core.ui.components.base.SmallText
import st.slex.csplashscreen.core.ui.theme.Dimen

@Composable
fun LazyListCollectionItem(
    item: CollectionModel,
    onProfileClick: (username: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {

    PhotosBaseItem(
        modifier = modifier,
        onContainerClick = remember(item.uuid) {
            { onCollectionClick(item.uuid) }
        },
        onHeaderClick = remember(item.username) {
            { onProfileClick(item.username) }
        },
        url = item.url,
        headerContent = {
            MainUserAvatar(item.userUrl)
            Spacer(modifier = Modifier.width(Dimen.medium))
            Column {
                MediumText(item.username)

            }
        }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.background.copy(
                        alpha = 0.7f
                    )
                )
                .padding(Dimen.small)
        ) {
            MediumText(item.title)
            Spacer(modifier = Modifier.height(Dimen.small))
            SmallText("Photos: ${item.totalPhotos}")
        }
    }
}