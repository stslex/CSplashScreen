package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.utiles.GET_COLLECTIONS
import st.slex.csplashscreen.utiles.GET_PHOTOS

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoroutinesApi
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val queryPhotos = listOf(GET_PHOTOS)
    val queryCollections = listOf(GET_COLLECTIONS)
    coroutineScope.launch {
        viewModel.setQueryPhotos(queryPhotos)
        viewModel.setQueryCollections(queryCollections)
    }

    val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
    val lazyPagingCollectionsItems = viewModel.collections.collectAsLazyPagingItems()
    Pager(lazyPagingPhotosItems, lazyPagingCollectionsItems, navController)
}

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun Pager(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    lazyPagingCollectionsItems: LazyPagingItems<CollectionModel>,
    navController: NavController
) {
    HorizontalPager(
        count = 2,
        contentPadding = PaddingValues(),
    ) { page ->
        LazyColumn {
            when (page) {
                0 -> {
                    items(lazyPagingPhotosItems) { item ->
                        ImageItem(item, navController, page, this@HorizontalPager)
                    }
                    lazyPagingPhotosItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                            }
                        }
                    }
                }
                1 -> {
                    items(lazyPagingCollectionsItems) { item ->
                        CollectionItem(item, navController, page, this@HorizontalPager)
                    }
                    lazyPagingCollectionsItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                                item { ImageItemLoading(page = page, scope = this@HorizontalPager) }
                            }
                        }
                    }
                }
            }

        }
    }
}
