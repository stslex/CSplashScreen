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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import st.slex.core.Resource
import st.slex.core.UtilsExtensions.convertUrl
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_ui.components.UserImageHeadWithUserName
import st.slex.feature_photo_detail.R

@Composable
fun ImageDetailScreen(
    arguments: List<String>,
    viewModel: DetailPhotoViewModel = hiltViewModel(),
    onImageClick: (url: String) -> Unit,
    onTagClick: (tag: String) -> Unit,
    onProfileClick: (username: String) -> Unit
) {
    val url: String = arguments[0]
    val id: String = arguments[1]

    val result: Resource<ImageModel> by remember(viewModel) {
        viewModel.getPhotoById(id)
    }.collectAsState(
        initial = Resource.Loading,
        context = Dispatchers.IO
    )

    SideEffect(sideEffect())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item { BindTopImageHead(url = url, onImageClick = onImageClick) }
        item { Spacer(Modifier.padding(4.dp)) }
        item {
            CheckReceivedData(
                result,
                viewModel::getUrlAndDownloadImage,
                onTagClick,
                onProfileClick
            )
        }
    }
}

@Composable
private fun sideEffect(
    systemUiController: SystemUiController = rememberSystemUiController(),
    darkIcons: Boolean = !isSystemInDarkTheme()
): () -> Unit = {
    systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
}

@Composable
private fun CheckReceivedData(
    result: Resource<ImageModel>,
    getUrlAndDownloadImage: (String) -> Unit,
    tagClickListener: (tag: String) -> Unit,
    onProfileClick: (username: String) -> Unit
) {
    when (result) {
        is Resource.Success -> with(result.data) {
            this.id
            Column {
                UserDetailImageHead(
                    username = user.username,
                    url = user.profileImageModel.medium,
                    onProfileClick = onProfileClick,
                    onDownloadClick = { getUrlAndDownloadImage(id) }
                )
                BindDetailScreenBody(tags = tags, tagClickListener = tagClickListener)
            }
        }

        is Resource.Loading -> BindDetailImageLoading(Modifier)
        is Resource.Failure -> BindDetailImageFailure()
    }
}

@Composable
private fun BindDetailScreenBody(
    tags: List<TagModel>,
    tagClickListener: (tag: String) -> Unit
) {
    Spacer(modifier = Modifier.size(16.dp))
    Divider()
    Spacer(modifier = Modifier.size(16.dp))
    if (tags.isNotEmpty()) BindTags(tags = tags, tagClickListener = tagClickListener)
    BindImageInformation()
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun BindTags(
    tags: List<TagModel>,
    tagClickListener: (tag: String) -> Unit
) {
    BindDetailImageBodyTags(tags) { tag ->
        tagClickListener(tag)
//        val destination = NavHostResource.SearchPhotosScreen.destination
//        val route = "$destination/$tag"
//        navController.navigate(route)
    }
    Spacer(modifier = Modifier.size(16.dp))
    Divider()
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
private fun BindImageInformation() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Show more information..."
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailImageHead(
    url: String,
    username: String,
    onDownloadClick: () -> Unit,
    onProfileClick: (username: String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val (userSurface, download) = createRefs()

        UserImageHeadWithUserName(
            modifier = Modifier.constrainAs(userSurface) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(download.start)
            },
            url = url,
            username = username,
            onProfileClick = onProfileClick
        )

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .wrapContentSize()
                .padding(start = 8.dp, end = 8.dp)
                .shadow(elevation = 16.dp, shape = CircleShape)
                .constrainAs(download) {
                    height = Dimension.fillToConstraints
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            shape = CircleShape,
            onClick = {
                onDownloadClick()
            }
        ) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_download),
                contentDescription = "Download",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
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
                val encodedUrl = url.convertUrl()
                onImageClick(encodedUrl)
//                val destination = NavHostResource.RawImageScreen.destination
//                val route = "$destination/$encodedUrl"
//                navController.navigate(route)
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
private inline fun BindDetailImageBodyTags(
    tags: List<TagModel>,
    crossinline onClick: (String) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(count = tags.size) { key ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClick(tags[key].title)
                    },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = tags[key].title,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun BindDetailImageLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
private fun BindDetailImageFailure() {

}



