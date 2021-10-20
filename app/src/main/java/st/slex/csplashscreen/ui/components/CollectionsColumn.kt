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
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.ui.navigation.NavActions
import st.slex.csplashscreen.ui.navigation.Navigator
import st.slex.csplashscreen.ui.theme.TransparentGray
import st.slex.csplashscreen.ui.theme.Typography

@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel,
    modifier: Modifier,
    navigator: Navigator,
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
                navigator = navigator
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        BindCoverImageCard(
            item.id,
            item.cover_photo.urls.regular,
            item.title,
            item.total_photos,
            navigator = navigator
        )
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindCoverImageCard(
    id: String,
    url: String,
    title: String,
    totalPhotos: Int,
    navigator: Navigator
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .shadow(elevation = 0.dp),
        elevation = 0.dp,
        onClick = {
            navigator.navigate(NavActions.SingleCollectionScreen(id))
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

