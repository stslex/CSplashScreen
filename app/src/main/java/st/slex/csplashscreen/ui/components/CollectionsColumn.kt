package st.slex.csplashscreen.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.theme.TransparentGray
import st.slex.csplashscreen.ui.theme.Typography

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@SuppressLint("RestrictedApi")
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun CollectionItem(
    item: CollectionModel,
    modifier: Modifier,
    isUserVisible: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isUserVisible) {
            UserImageHeadWithUserName(
                modifier = Modifier.fillMaxWidth(),
                url = item.user.profile_image.medium,
                username = item.user.username
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        BindCoverImageCard(
            item.id,
            item.cover_photo.urls.regular,
            item.title,
            item.total_photos
        )
    }
}

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindCoverImageCard(
    id: String,
    url: String,
    title: String,
    totalPhotos: Int,
    navigator: Navigator = LocalNavigator.currentOrThrow
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .shadow(elevation = 0.dp),
        elevation = 0.dp,
        onClick = {
            navigator.push(SingleCollectionScreen(id))
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

