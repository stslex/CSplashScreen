package st.slex.feature_user.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.base.MainUserAvatar
import com.stslex.csplashscreen.core.ui.components.base.MediumText
import com.stslex.csplashscreen.core.ui.components.base.PhotosBaseItem
import com.stslex.csplashscreen.core.ui.components.base.PhotosBaseLazyList
import com.stslex.csplashscreen.core.ui.components.base.SmallText
import com.stslex.csplashscreen.core.ui.theme.Dimen
import st.slex.feature_user.ui.components.tabs.UserTabs
import st.slex.feature_user.ui.components.tabs.UserTabsRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserContent(
    photos: LazyPagingItems<PhotoModel>,
    likes: LazyPagingItems<PhotoModel>,
    collections: LazyPagingItems<CollectionModel>,
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState {
        UserTabs.values().size
    }

    Column(
        modifier = modifier
    ) {
        UserTabsRow(pagerState)

        HorizontalPager(
            state = pagerState
        ) { pageNumber ->
            when (UserTabs.getByIndex(pageNumber)) {
                UserTabs.PHOTOS -> {
                    LazyListPhotos(
                        items = photos,
                        onUserClick = onUserClick,
                        onImageClick = onImageClick
                    )
                }

                UserTabs.LIKE -> {
                    LazyListPhotos(
                        items = likes,
                        onUserClick = onUserClick,
                        onImageClick = onImageClick
                    )
                }

                UserTabs.COLLECTION -> {
                    PhotosBaseLazyList(
                        modifier = modifier,
                        items = collections,
                        key = { item ->
                            item.uuid
                        },
                        contentType = {
                            "COLLECTIONS"
                        },
                    ) { item ->
                        PhotosBaseItem(
                            modifier = modifier,
                            onContainerClick = remember(item.uuid) {
                                { onCollectionClick(item.uuid) }
                            },
                            onHeaderClick = remember(item.username) {
                                { onUserClick(item.username) }
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
                }
            }
        }
    }
}