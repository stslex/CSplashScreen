package st.slex.csplashscreen.ui.screens.user

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.user.UserModel
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.theme.Typography

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun UserScreen(
    navController: NavController,
    username: String,
    viewModel: UserViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val result: Resource<UserModel> by remember(viewModel) {
        viewModel.getUser(username = username)
    }.collectAsState(Resource.Loading)

    Scaffold(
        topBar = {
            BindUserTopAppBar(
                username = username,
                navController = navController
            )
        }
    ) {
        when (result) {
            is Resource.Success -> {
                val user = (result as Resource.Success<UserModel>).data
                BindUserScreen(user = user, navController = navController)
            }
            is Resource.Failure -> {

            }
            is Resource.Loading -> {

            }
        }
    }

}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun BindUserScreen(user: UserModel, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        BindUserHeader(
            total_photos = user.total_photos.toString(),
            total_likes = user.total_likes.toString(),
            total_collections = user.total_collections.toString(),
            url = user.profile_image?.large.toString()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        Spacer(modifier = Modifier.size(16.dp))
        if (user.bio.isNotEmpty()) {
            BindUserBio(bio = user.bio)
            Spacer(modifier = Modifier.size(16.dp))
            Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun BindUserBio(bio: String) {
    val maxLinesOfBio = remember { mutableStateOf(1) }
    Surface(
        elevation = 16.dp,
        onClick = {
            maxLinesOfBio.value = if (maxLinesOfBio.value == 1) Int.MAX_VALUE else 1
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bio",
                style = Typography.body1
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnimatedContent(
                targetState = maxLinesOfBio.value
            ) { target ->
                Text(
                    text = bio,
                    maxLines = target,
                    style = Typography.body2,
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BindUserHeader(
    total_photos: String,
    total_likes: String,
    total_collections: String,
    url: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Image(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .clip(CircleShape)
                .width(64.dp),
            painter = rememberImagePainter(
                data = url,
                builder = {
                    allowHardware(false)
                }
            ),
            contentDescription = "User Profile Image",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextHeaderColumn("Photos", total_photos)
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Likes", total_likes)
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Collections", total_collections)
        }
    }
}

@Composable
fun BindUserTopAppBar(username: String, navController: NavController) {
    TopAppBar {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = "return"
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = username,
                textAlign = TextAlign.Start,
                maxLines = 1,
                style = Typography.h6
            )
        }
    }
}

@Composable
fun TextHeaderColumn(title: String, contentTitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = Typography.body1
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contentTitle,
            style = Typography.body2
        )
    }
}