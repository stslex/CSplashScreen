package st.slex.feature_photo_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.Glide
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import com.stslex.csplashscreen.core.Resource
import com.stslex.csplashscreen.core.UtilsExtensions.convertedUrl
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_ui.components.UserImageHeadWithUserName
import st.slex.feature_photo_detail.ui.components.DetailImageBodyTags
import st.slex.feature_photo_detail.ui.components.DownloadImageButton
import st.slex.feature_photo_detail.ui.components.WallPaperButton

@Composable
fun ImageDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailPhotoViewModel
) {
    val result: Resource<ImageModel> by remember(viewModel) {
        viewModel.photoById
    }.collectAsState(
        initial = Resource.Loading,
        context = Dispatchers.IO
    )
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme().not()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            BindTopImageHead(url = viewModel.imageUrl, onImageClick = viewModel::onImageClick)
        }
        item {
            Spacer(Modifier.padding(4.dp))
        }
        item(content = result.checkLoadedState(viewModel = viewModel))
    }
}

private fun Resource<ImageModel>.checkLoadedState(
    viewModel: DetailPhotoViewModel
): @Composable LazyItemScope.() -> Unit = {
    when (this@checkLoadedState) {
        is Resource.Success -> BindSuccessLoaded(
            imageModel = data,
            onDownloadImageClick = viewModel::onDownloadImageClick,
            onTagClick = viewModel::onTagClick,
            onProfileClick = viewModel::onProfileClick,
            onSetWallpaperClick = viewModel::onWallpaperSetClick
        )

        is Resource.Loading -> BindDetailImageLoading(Modifier)
        is Resource.Failure -> BindDetailImageFailure()
    }
}

@Composable
private fun BindSuccessLoaded(
    imageModel: ImageModel,
    onProfileClick: (String) -> Unit,
    onDownloadImageClick: (String) -> Unit,
    onTagClick: (String) -> Unit,
    onSetWallpaperClick: (url: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserDetailImageHead(
            imageModel = imageModel,
            onProfileClick = onProfileClick,
            onDownloadImageClick = onDownloadImageClick,
            onSetWallpaperClick = onSetWallpaperClick
        )
        Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        if (imageModel.tags.isNotEmpty()) {
            DetailImageBodyTags(tags = imageModel.tags, onClick = onTagClick)
            Divider(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        }
        BindImageInformation()
    }
}

@Composable
private fun BindImageInformation() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Show more information..."
        )
    }
}

@Composable
private fun UserDetailImageHead(
    imageModel: ImageModel,
    onDownloadImageClick: (url: String) -> Unit,
    onProfileClick: (username: String) -> Unit,
    onSetWallpaperClick: (url: String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val (userSurface, wallpaper, download) = createRefs()
        UserImageHeadWithUserName(
            modifier = Modifier.constrainAs(userSurface) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(wallpaper.start)
            },
            url = imageModel.user.profileImageModel.medium,
            username = imageModel.user.username,
            onProfileClick = onProfileClick,
        )
        DownloadImageButton(
            modifier = Modifier.constrainAs(download) {
                height = Dimension.fillToConstraints
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            onDownloadImageClick = {
                onDownloadImageClick(imageModel.urls.raw)
            }
        )
        WallPaperButton(
            modifier = Modifier.constrainAs(wallpaper) {
                height = Dimension.fillToConstraints
                end.linkTo(download.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            onSetWallpaperClick = {
                onSetWallpaperClick(imageModel.urls.raw)
            }
        )
    }
}

@Composable
private fun BindTopImageHead(
    url: String,
    onImageClick: (url: String) -> Unit
) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
            .clickable {
                onImageClick(url.convertedUrl)
            },
        imageModel = url,
        contentScale = ContentScale.FillBounds,
        circularReveal = CircularReveal(duration = 1000),
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}

@Composable
private fun BindDetailImageLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
private fun BindDetailImageFailure() {

}



