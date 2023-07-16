package com.stslex.csplashscreen.feature.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.component.LazyListCollection
import com.stslex.csplashscreen.core.collection.ui.model.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.home.ui.components.tabs.MainScreenTabRow
import com.stslex.csplashscreen.feature.home.ui.components.tabs.MainScreenTabs
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navToProfile: (username: String) -> Unit,
    navToImage: (imageId: String) -> Unit,
    navToCollection: (id: String) -> Unit,
    collections: LazyPagingItems<CollectionModel>,
    photos: LazyPagingItems<PhotoModel>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val photosListState = rememberLazyListState()
    val collectionListState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        MainScreenTabRow(
            modifier = Modifier,
            pagerState = pagerState,
            onClick = { pageNumber ->
                when (pageNumber) {
                    MainScreenTabs.COLLECTIONS.pageNum -> coroutineScope.launch {
                        collectionListState.animateScrollToItem(0)
                    }

                    MainScreenTabs.PHOTOS.pageNum -> coroutineScope.launch {
                        photosListState.animateScrollToItem(0)
                    }

                    else -> throw IllegalStateException("Pager index out of bound")
                }
            }
        )

        HorizontalPager(
            state = pagerState,
            pageCount = MainScreenTabs.values().size
        ) { pageNumber ->
            when (pageNumber) {

                MainScreenTabs.COLLECTIONS.pageNum -> {
                    LazyListCollection(
                        items = collections,
                        onProfileClick = navToProfile,
                        onCollectionClick = navToCollection,
                        listState = collectionListState,
                        contentType = { MainScreenTabs.COLLECTIONS }
                    )
                }

                MainScreenTabs.PHOTOS.pageNum -> {
                    LazyListPhotos(
                        items = photos,
                        onImageClick = navToImage,
                        onUserClick = navToProfile,
                        listState = photosListState,
                        contentType = { MainScreenTabs.PHOTOS }
                    )
                }

                else -> throw IllegalStateException("Pager index out of bound")
            }
        }
    }
}
