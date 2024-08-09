package st.slex.csplashscreen.feature.user.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.collection.ui.component.LazyListCollection
import st.slex.csplashscreen.core.photos.ui.component.LazyListPhotos
import st.slex.csplashscreen.feature.user.ui.components.tabs.UserTab
import st.slex.csplashscreen.feature.user.ui.components.tabs.UserTabsRow
import st.slex.csplashscreen.feature.user.ui.state.UserPagerState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserPager(
    userPagerState: UserPagerState,
    onUserClick: (username: String) -> Unit,
    onImageClick: (id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val coroutineScope = rememberCoroutineScope()
        UserTabsRow(
            pagerState = userPagerState.pagerState,
            userTabs = userPagerState.userTabs,
            onClick = {
                coroutineScope.launch {
                    userPagerState.scrollToTop(it)
                }
            }
        )

        HorizontalPager(
            state = userPagerState.pagerState,
        ) { pageNumber ->
            when (userPagerState.getTab(pageNumber)) {
                UserTab.PHOTOS -> {
                    LazyListPhotos(
                        items = userPagerState.photos,
                        onUserClick = onUserClick,
                        onImageClick = onImageClick,
                        listState = userPagerState.photosListState,
                        contentType = { UserTab.PHOTOS }
                    )
                }

                UserTab.LIKE -> {
                    LazyListPhotos(
                        items = userPagerState.likes,
                        onUserClick = onUserClick,
                        onImageClick = onImageClick,
                        listState = userPagerState.likesListState,
                        contentType = { UserTab.LIKE }
                    )
                }

                UserTab.COLLECTION -> {
                    LazyListCollection(
                        items = userPagerState.collections,
                        onProfileClick = onUserClick,
                        onCollectionClick = onCollectionClick,
                        listState = userPagerState.collectionsListState,
                        contentType = { UserTab.COLLECTION }
                    )
                }

                null -> Unit
            }
        }
    }
}
