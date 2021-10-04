package st.slex.csplashscreen.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.MainViewModel
import st.slex.csplashscreen.utiles.GET_COLLECTIONS
import st.slex.csplashscreen.utiles.GET_PHOTOS

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalCoroutinesApi
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val queryPhotos = listOf(GET_PHOTOS)
    val queryCollections = listOf(GET_COLLECTIONS)

    viewModel.setQueryPhotos(queryPhotos)
    viewModel.setQueryCollections(queryCollections)

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
    val mapPages = mutableMapOf(
        "Photos" to 0,
        "Collections" to 1
    )

    HorizontalPager(
        count = mapPages.size,
        contentPadding = PaddingValues(),
    ) { page ->
        LazyColumn {
            when (page) {
                mapPages.getValue("Photos") -> {
                    items(lazyPagingPhotosItems) { item ->
                        ImageItem(item, navController, page, this@HorizontalPager)
                    }
                    lazyPagingPhotosItems.checkState {
                        loadState(page = page, scope = this@HorizontalPager)
                    }
                }
                mapPages.getValue("Collections") -> {
                    items(lazyPagingCollectionsItems) { item ->
                        CollectionItem(item, navController)
                    }
                    lazyPagingCollectionsItems.checkState {
                        loadState(page = page, scope = this@HorizontalPager)
                    }
                }
            }

        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
fun LazyListScope.loadState(page: Int = 0, scope: PagerScope? = null) = repeat(3) {
    item { ImageItemLoading(page = page, scope = scope) }
}


inline fun <T : Any> LazyPagingItems<T>.checkState(crossinline function: () -> Unit) {
    when {
        loadState.refresh is LoadState.Loading -> function()
        loadState.append is LoadState.Loading -> function()
    }
}


