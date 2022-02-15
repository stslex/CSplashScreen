package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.UtilsExtensions.convertUrl
import st.slex.csplashscreen.ui.navigation.NavHostResource


@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun ListOfElements(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    navController: NavController
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(state = lazyListState) {
        items(lazyPagingPhotosItems, key = { it.id }) { item ->
            ImageItem(
                item = item,
                navController = navController,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
                    .setScrollingColumnAnimation(lazyListState, item?.id)
            )
        }
        lazyPagingPhotosItems.checkState(this)
    }
}

@ExperimentalMaterial3Api
@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ImageItem(
    item: ImageModel?,
    modifier: Modifier,
    navController: NavController,
    isUserVisible: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (isUserVisible) UserImageHeadWithUserName(
            modifier = Modifier.fillMaxWidth(),
            url = item?.user?.profile_image?.medium.toString(),
            username = item?.user?.username.toString(),
            navController = navController
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Surface(
            modifier = Modifier.clickable {
                val id = item?.id
                val encodedUrl = item?.urls?.regular?.convertUrl()
                val destination = NavHostResource.ImageDetailScreen.destination
                val route = "$destination/$encodedUrl/$id"
                navController.navigate(route)
            },
            content = { CoverPhotoItem(url = item?.urls?.regular.toString()) }
        )
    }
}