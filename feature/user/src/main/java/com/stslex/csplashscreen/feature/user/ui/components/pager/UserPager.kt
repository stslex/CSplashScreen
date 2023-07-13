package com.stslex.csplashscreen.feature.user.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.component.LazyListCollection
import com.stslex.csplashscreen.core.collection.ui.model.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTabs
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTabsRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserContent(
    photos: LazyPagingItems<PhotoModel>,
    likes: LazyPagingItems<PhotoModel>,
    collections: LazyPagingItems<CollectionModel>,
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    lazyListState: LazyListState,
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
                        onImageClick = onImageClick,
                        listState = lazyListState
                    )
                }

                UserTabs.LIKE -> {
                    LazyListPhotos(
                        items = likes,
                        onUserClick = onUserClick,
                        onImageClick = onImageClick,
                        listState = lazyListState
                    )
                }

                UserTabs.COLLECTION -> {
                    LazyListCollection(
                        items = collections,
                        onProfileClick = onUserClick,
                        onCollectionClick = onCollectionClick,
                        listState = lazyListState
                    )
                }
            }
        }
    }
}