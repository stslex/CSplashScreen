package st.slex.csplashscreen.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.flow.SharedFlow
import st.slex.csplashscreen.R
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.image.TagModel
import st.slex.csplashscreen.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.theme.Shapes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject


interface ImageDetailScreen {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @Composable
    fun BindScreen(
        url: String,
        navController: NavController,
        imageFlow: () -> SharedFlow<UIResult<ImageModel>>
    )

    class Screen @Inject constructor() : ImageDetailScreen {

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @Composable
        override fun BindScreen(
            url: String,
            navController: NavController,
            imageFlow: () -> SharedFlow<UIResult<ImageModel>>
        ) {
            val uiResult = imageFlow().collectAsState(initial = null).value

            Column {
                BindTopImageHead(url = url, navController = navController)
                Spacer(modifier = Modifier.padding(4.dp))
                when (uiResult) {
                    is UIResult.Success -> {
                        BindDetailImageBody(image = uiResult.data, navController = navController)
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

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @Composable
        private fun BindDetailImageBody(image: ImageModel, navController: NavController) {
            UserDetailImageHead(
                username = image.user?.username.toString(),
                url = image.user?.profile_image?.medium.toString(),
                navController = navController
            )
            Spacer(modifier = Modifier.size(16.dp))
            Divider()
            Spacer(modifier = Modifier.size(16.dp))
            if (!image.tags.isNullOrEmpty()) {
                BindDetailImageBodyTags(image.tags) {
                    navController.navigate("search_photos/$it")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Divider()
                Spacer(modifier = Modifier.size(16.dp))
            }

        }

        @ExperimentalMaterialApi
        @ExperimentalCoilApi
        @Composable
        fun UserDetailImageHead(
            url: String,
            username: String,
            navController: NavController
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

        @ExperimentalCoilApi
        @Composable
        private fun BindTopImageHead(url: String, navController: NavController) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                        navController.navigate("raw_image/$encodedUrl")
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



