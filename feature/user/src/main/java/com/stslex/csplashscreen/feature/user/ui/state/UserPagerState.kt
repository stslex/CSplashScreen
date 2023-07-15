package com.stslex.csplashscreen.feature.user.ui.state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.model.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTab

@OptIn(ExperimentalFoundationApi::class)
@Stable
data class UserPagerState(
    val photos: LazyPagingItems<PhotoModel>,
    val likes: LazyPagingItems<PhotoModel>,
    val collections: LazyPagingItems<CollectionModel>,
    val photosListState: LazyListState,
    val collectionsListState: LazyListState,
    val likesListState: LazyListState,
    val userTabs: Set<UserTab>,
    val pagerState: PagerState,
) {

    val isOnPreFlingAllow: Boolean
        get() = when (userTabs.toList().getOrNull(pagerState.currentPage)) {
            UserTab.PHOTOS -> photosListState.firstVisibleItemScrollOffset == 0
            UserTab.LIKE -> likesListState.firstVisibleItemScrollOffset == 0
            UserTab.COLLECTION -> collectionsListState.firstVisibleItemScrollOffset == 0
            else -> false
        }

    fun getTab(index: Int): UserTab? = userTabs.toList().getOrNull(index)

    suspend fun navToPage(tab: UserTab) {
        val page = userTabs.indexOf(tab)
        pagerState.animateScrollToPage(
            page = page
        )
    }

    suspend fun scrollToTop(page: Int) {
        val listState = when (getTab(page)) {
            UserTab.PHOTOS -> photosListState
            UserTab.COLLECTION -> collectionsListState
            UserTab.LIKE -> likesListState
            null -> throw IllegalStateException()
        }
        listState.animateScrollToItem(0)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberUserPagerState(
    photos: LazyPagingItems<PhotoModel>,
    likes: LazyPagingItems<PhotoModel>,
    collections: LazyPagingItems<CollectionModel>,
): UserPagerState {

    val userTabs = remember(
        key1 = photos.itemCount,
        key2 = likes.itemCount,
        key3 = collections.itemCount
    ) {
        mutableSetOf<UserTab>().apply {
            if (photos.itemCount != 0) {
                add(UserTab.PHOTOS)
            }
            if (likes.itemCount != 0) {
                add(UserTab.LIKE)
            }
            if (collections.itemCount != 0) {
                add(UserTab.COLLECTION)
            }
        }
    }

    val pagerState = rememberPagerState()
    val photosListState = rememberLazyListState()
    val likesListState = rememberLazyListState()
    val collectionsListState = rememberLazyListState()

    return remember(userTabs) {
        UserPagerState(
            photos = photos,
            likes = likes,
            collections = collections,
            userTabs = userTabs,
            pagerState = pagerState,
            photosListState = photosListState,
            likesListState = likesListState,
            collectionsListState = collectionsListState
        )
    }
}
