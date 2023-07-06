package st.slex.feature_main.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import st.slex.core_photos.ui.PhotoModel
import st.slex.feature_main.ui.components.collections.MainScreenCollections
import st.slex.feature_main.ui.components.images.MainScreenImages
import st.slex.feature_main.ui.components.tabs.MainScreenTabRow
import st.slex.feature_main.ui.components.tabs.MainScreenTabs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navToProfile: (username: String) -> Unit,
    navToImage: (url: String, imageId: String) -> Unit,
    navToCollection: (id: String) -> Unit,
    collections: LazyPagingItems<CollectionModel>,
    photos: LazyPagingItems<PhotoModel>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        pageCount = { 2 }
    )
    Column(
        modifier = modifier
    ) {
        MainScreenTabRow(
            modifier = Modifier,
            pagerState = pagerState,
        )
        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            key = null
        ) { pageNumber ->
            when (pageNumber) {

                MainScreenTabs.COLLECTIONS.pageNum -> {
                    MainScreenCollections(
                        items = collections,
                        onProfileClick = navToProfile,
                        onCollectionClick = navToCollection
                    )
                }

                MainScreenTabs.PHOTOS.pageNum -> {
                    MainScreenImages(
                        items = photos,
                        onImageClick = navToImage,
                        onUserClick = navToProfile
                    )
                }

                else -> throw IllegalStateException("Pager index out of bound")
            }
        }
    }
}
