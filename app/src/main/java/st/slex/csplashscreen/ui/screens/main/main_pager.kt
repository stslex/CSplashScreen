package st.slex.csplashscreen.ui.screens.main

import android.os.Parcelable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.components.CollectionItem
import st.slex.csplashscreen.ui.components.ImageItem
import st.slex.csplashscreen.ui.components.animatePager
import st.slex.csplashscreen.ui.components.checkState

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Pager(
    pagesResource: List<MainPagerTabResource<out Parcelable>>,
    pagerState: PagerState,
    navController: NavController
) {
    HorizontalPager(
        count = pagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()
        val pagingResource = pagesResource[pageNumber]

        @Composable
        fun Parcelable.SetItemDependsOfType(id: String) {
            val animateModifier: Modifier = Modifier.animatePager(
                this@HorizontalPager, pageNumber, listState, id
            )
            SetCurrentItem(navController = navController, modifier = animateModifier)
        }

        LazyColumn(state = listState) {
            when (pagingResource) {
                is MainPagerTabResource.Photos -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }

                is MainPagerTabResource.Collections -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id)
                    }
                }
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Parcelable.SetCurrentItem(
    navController: NavController,
    modifier: Modifier
) {
    if (this is ImageModel) {
        ImageItem(item = this, modifier = modifier, navController = navController)
    } else if (this is CollectionModel) {
        CollectionItem(item = this, modifier = modifier, navController = navController)
    }
}