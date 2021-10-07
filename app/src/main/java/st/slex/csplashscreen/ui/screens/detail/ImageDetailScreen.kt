package st.slex.csplashscreen.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavBackStackEntry
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.R
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.image.TagModel
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.navigation.NavigationState
import st.slex.csplashscreen.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
interface ImageDetailScreen {


    @Composable
    fun BindScreen(
        args: NavBackStackEntry,
        viewModel: DetailPhotoViewModel
    )

    class Base @Inject constructor() : ImageDetailScreen {

        @Composable
        override fun BindScreen(
            args: NavBackStackEntry,
            viewModel: DetailPhotoViewModel
        ) {
            val url = args.arguments?.getString("url").toString()
            val id = args.arguments?.getString("imageId").toString()

            val uiResult by remember(viewModel) {
                viewModel.getCurrentPhoto(id)
            }.collectAsState(initial = UIResult.Loading)

            Column {
                BindTopImageHead(url = url, navigation = { destination, args ->
                    viewModel.navigate(destination, args)
                })
                Spacer(modifier = Modifier.padding(4.dp))
                when (uiResult) {
                    is UIResult.Success -> {
                        val image = (uiResult as UIResult.Success<ImageModel>).data
                        BindDetailImageBody(image = image, navigation = { destination, args ->
                            viewModel.navigate(destination, args)
                        })
                    }
                    is UIResult.Loading -> {
                        BindDetailImageLoading(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    is UIResult.Failure -> {
                        BindDetailImageFailure()
                    }
                }
            }
        }

        @Composable
        private fun BindDetailImageBody(
            image: ImageModel,
            navigation: (NavigationState, List<String>) -> Unit
        ) {
            UserDetailImageHead(
                username = image.user?.username.toString(),
                url = image.user?.profile_image?.medium.toString(),
                navigation = navigation
            )
            Spacer(modifier = Modifier.size(16.dp))
            Divider()
            Spacer(modifier = Modifier.size(16.dp))
            if (!image.tags.isNullOrEmpty()) {
                BindDetailImageBodyTags(image.tags) {
                    navigation(NavigationState.SearchPhotosScreen, listOf(it))
                }
                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))
            }

        }

        @Composable
        private fun UserDetailImageHead(
            url: String,
            username: String,
            navigation: (NavigationState, List<String>) -> Unit
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
                    navigation = navigation
                )

                Surface(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 8.dp, end = 8.dp)
                        .shadow(elevation = 16.dp, Shapes.medium)
                        .clip(RoundedCornerShape(16.dp))
                        .constrainAs(download) {
                            height = Dimension.fillToConstraints
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    onClick = {

                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        painter = painterResource(
                            id = R.drawable.ic_baseline_arrow_download
                        ),
                        contentDescription = "Download"
                    )
                }

            }


        }

        @Composable
        private inline fun BindTopImageHead(
            url: String,
            crossinline navigation: (NavigationState, List<String>) -> Unit
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        navigation(NavigationState.RawImageScreen, listOf(encodedUrl))
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
                            onClick(tags[key].title.toString())
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = tags[key].title.toString(),
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
    }


}



