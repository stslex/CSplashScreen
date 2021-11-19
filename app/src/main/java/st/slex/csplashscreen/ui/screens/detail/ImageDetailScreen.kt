package st.slex.csplashscreen.ui.screens.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.R
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.image.TagModel
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.navigation.NavHostResource
import st.slex.csplashscreen.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ImageDetailScreen(
    navController: NavController,
    arguments: List<String>,
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: DetailPhotoViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val url: String = arguments[0]
    val id: String = arguments[1]

    val result: Resource<ImageModel> by remember(viewModel) {
        viewModel.getCurrentPhoto(id)
    }.collectAsState(initial = Resource.Loading, context = Dispatchers.IO)

    val darkIcons = !isSystemInDarkTheme()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { BindTopImageHead(url = url, navController = navController) }
        item { Spacer(modifier = Modifier.padding(4.dp)) }

        when (result) {
            is Resource.Success -> {
                val image = (result as Resource.Success<ImageModel>).data
                item {
                    UserDetailImageHead(
                        username = image.user.username,
                        url = image.user.profile_image.medium,
                        navController = navController
                    ) {
                        viewModel.getUrlAndDownloadImage(id)
                    }
                }
                item { BindDetailScreenBody(image = image, navController = navController) }
            }
            is Resource.Loading -> {
                item { BindDetailImageLoading(modifier = Modifier) }
            }
            is Resource.Failure -> {
                item { BindDetailImageFailure() }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun BindDetailScreenBody(
    image: ImageModel,
    navController: NavController
) {
    Spacer(modifier = Modifier.size(16.dp))
    Divider()
    Spacer(modifier = Modifier.size(16.dp))
    if (!image.tags.isNullOrEmpty()) {
        BindDetailImageBodyTags(image.tags) { tag ->
            val destination = NavHostResource.SearchPhotosScreen.destination
            val route = "$destination/$tag"
            navController.navigate(route)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Divider()
        Spacer(modifier = Modifier.size(16.dp))
    }
    BindImageInformation(image)
    Spacer(modifier = Modifier.size(16.dp))
}

@ExperimentalMaterialApi
@Composable
private fun BindImageInformation(image: ImageModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        elevation = 16.dp,
        onClick = {

        }
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Show more information..."
        )
    }
}

@ExperimentalCoilApi
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

@ExperimentalCoilApi
@Composable
private fun BindTopImageHead(
    url: String,
    navController: NavController
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                val destination = NavHostResource.RawImageScreen.destination
                val route = "$destination/$encodedUrl"
                navController.navigate(route)
            },
        painter = rememberImagePainter(
            data = url,
            builder = {
                transformations(RoundedCornersTransformation())
                allowHardware(false)
            }
        ),
        contentDescription = "Image"
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
            Surface(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .shadow(elevation = 16.dp, Shapes.medium)
                    .clip(RoundedCornerShape(16.dp)),
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



