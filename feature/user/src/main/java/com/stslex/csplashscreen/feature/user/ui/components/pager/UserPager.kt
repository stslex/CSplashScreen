package com.stslex.csplashscreen.feature.user.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex.csplashscreen.core.collection.ui.component.LazyListCollection
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTab
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTabsRow
import com.stslex.csplashscreen.feature.user.ui.state.UserPagerState
import com.stslex.csplashscreen.feature.user.ui.state.UserScreenNavigation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserPager(
    userPagerState: UserPagerState,
    navigation: UserScreenNavigation,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserTabsRow(
            pagerState = userPagerState.pagerState,
            userTabs = userPagerState.userTabs,
            onClick = userPagerState::scrollToTop
        )

        HorizontalPager(
            state = userPagerState.pagerState,
            pageCount = userPagerState.userTabs.size
        ) { pageNumber ->
            when (userPagerState.getTab(pageNumber)) {
                UserTab.PHOTOS -> {
                    LazyListPhotos(
                        items = userPagerState.photos,
                        onUserClick = navigation.onUserClick,
                        onImageClick = navigation.onImageClick,
                        listState = userPagerState.photosListState,
                        contentType = { UserTab.PHOTOS }
                    )
                }

                UserTab.LIKE -> {
                    LazyListPhotos(
                        items = userPagerState.likes,
                        onUserClick = navigation.onUserClick,
                        onImageClick = navigation.onImageClick,
                        listState = userPagerState.likesListState,
                        contentType = { UserTab.LIKE }
                    )
                }

                UserTab.COLLECTION -> {
                    LazyListCollection(
                        items = userPagerState.collections,
                        onProfileClick = navigation.onUserClick,
                        onCollectionClick = navigation.onCollectionClick,
                        listState = userPagerState.collectionsListState,
                        contentType = { UserTab.COLLECTION }
                    )
                }

                null -> Unit
            }
        }
    }
}
