package st.slex.csplashscreen.ui.screens.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import st.slex.csplashscreen.R
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.image.TagModel
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.core.UtilsExtensions.convertUrl
import st.slex.csplashscreen.ui.navigation.NavHostResource

@FlowPreview
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun ImageDetailScreen(
    navController: NavController,
    arguments: List<String>,
    viewModel: DetailPhotoViewModel = hiltViewModel()
) {
    val url: String = arguments[0]
    val id: String = arguments[1]

    val result: Resource<ImageModel> by remember(viewModel) {
        viewModel.getCurrentPhoto(id)
    }.collectAsState(initial = Resource.Loading, context = Dispatchers.IO)

    SideEffect(sideEffect())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { BindTopImageHead(url = url, navController = navController) }
        item { Spacer(Modifier.padding(4.dp)) }
        item { CheckReceivedData(result, navController, viewModel::getUrlAndDownloadImage) }
    }
}

@Composable
private fun sideEffect(
    systemUiController: SystemUiController = rememberSystemUiController(),
    darkIcons: Boolean = !isSystemInDarkTheme()
): () -> Unit = {
    systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
}

@ExperimentalMaterialApi
@Composable
private fun CheckReceivedData(
    result: Resource<ImageModel>,
    navController: NavController,
    getUrlAndDownloadImage: (String) -> Job
) {
    when (result) {
        is Resource.Success -> result.data.BindSuccessResult(navController, getUrlAndDownloadImage)
        is Resource.Loading -> BindDetailImageLoading(Modifier)
        is Resource.Failure -> BindDetailImageFailure()
    }
}

@ExperimentalMaterialApi
@Composable
private fun ImageModel.BindSuccessResult(
    navController: NavController,
    getUrlAndDownloadImage: (String) -> Job
) {
    Column {
        UserDetailImageHead(
            username = user.username,
            url = user.profile_image.medium,
            navController = navController
        ) {
            getUrlAndDownloadImage.invoke(id)
        }
        BindDetailScreenBody(tags = tags, navController = navController)
    }
}

@ExperimentalMaterialApi
@Composable
private fun BindDetailScreenBody(
    tags: List<TagModel>,
    navController: NavController
) {
    Spacer(modifier = Modifier.size(16.dp))
    Divider()
    Spacer(modifier = Modifier.size(16.dp))
    if (tags.isNotEmpty()) BindTags(tags = tags, navController = navController)
    BindImageInformation()
    Spacer(modifier = Modifier.size(16.dp))
}

@ExperimentalMaterialApi
@Composable
private fun BindTags(tags: List<TagModel>, navController: NavController) {
    BindDetailImageBodyTags(tags) { tag ->
        val destination = NavHostResource.SearchPhotosScreen.destination
        val route = "$destination/$tag"
        navController.navigate(route)
    }
    Spacer(modifier = Modifier.size(16.dp))
    Divider()
    Spacer(modifier = Modifier.size(16.dp))
}

@ExperimentalMaterialApi
@Composable
private fun BindImageInformation() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        elevation = 16.dp
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Show more information..."
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun UserDetailImageHead(
    url: String,
    username: String,
    navController: NavController,
    downloadFunction: () -> Unit
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
            navController = navController
        )

        Surface(
            modifier = Modifier
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
                downloadFunction()
            }
        ) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_download),
                contentDescription = "Download"
            )
        }
    }
}

@Composable
private fun BindTopImageHead(
    url: String,
    navController: NavController
) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
            .clickable {
                val encodedUrl = url.convertUrl()
                val destination = NavHostResource.RawImageScreen.destination
                val route = "$destination/$encodedUrl"
                navController.navigate(route)
            },
        imageModel = url,
        contentScale = ContentScale.FillBounds,
        circularReveal = CircularReveal(duration = 1000),
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}

@ExperimentalMaterialApi
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
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 16.dp,
                onClick = {
                    onClick(tags[key].title)
                }
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



