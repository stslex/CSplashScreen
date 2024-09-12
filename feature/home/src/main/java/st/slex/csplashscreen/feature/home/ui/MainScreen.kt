package st.slex.csplashscreen.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.collection.ui.component.LazyListCollection
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.photos.ui.component.LazyListPhotos
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.feature.home.ui.component.tabs.MainScreenTabRow
import st.slex.csplashscreen.feature.home.ui.component.tabs.MainScreenTabs

@Composable
fun MainScreen(
    navToProfile: (username: String) -> Unit,
    navToImage: (imageId: String) -> Unit,
    navToCollection: (id: String) -> Unit,
    collections: LazyPagingItems<CollectionModel>,
    photos: LazyPagingItems<PhotoModel>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState {
        MainScreenTabs.entries.size
    }

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
