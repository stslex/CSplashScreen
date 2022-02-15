package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.ui.navigation.NavHostResource
import st.slex.csplashscreen.ui.theme.TransparentGray
import st.slex.csplashscreen.ui.theme.Typography

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CollectionItem(
    item: CollectionModel,
    modifier: Modifier,
    navController: NavController,
    isUserVisible: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profile_image.medium,
                username = item.user.username,
                navController = navController
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        BindCoverImageCard(
            item.id,
            item.cover_photo.urls.regular,
            item.title,
            item.total_photos,
            navController = navController
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindCoverImageCard(
    id: String,
    url: String,
    title: String,
    totalPhotos: Int,
    navController: NavController
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .shadow(elevation = 0.dp),
        elevation = 0.dp,
        onClick = {
            val destination = NavHostResource.CollectionScreen.destination
            val route = "$destination/$id"
            navController.navigate(route)
        }
    ) {
        CoverPhotoItem(
            url = url,
            modifier = Modifier
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = TransparentGray
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CollectionTextCard(text = title, Typography.h4)
            Spacer(modifier = Modifier.padding(4.dp))
            CollectionTextCard(text = "$totalPhotos Photos", style = Typography.h5)
        }
    }
}

