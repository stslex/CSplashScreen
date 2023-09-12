package st.slex.csplashscreen.feature.feature_photo_detail.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import st.slex.csplashscreen.core.ui.components.ImageComponent
import st.slex.csplashscreen.core.ui.components.UserImageHeadWithUserName
import st.slex.csplashscreen.core.ui.theme.Dimen
import st.slex.csplashscreen.feature.feature_photo_detail.R
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import st.slex.csplashscreen.feature.feature_photo_detail.ui.components.DetailImageBodyTags
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ImageDetailScreen(
    imageDetail: () -> StateFlow<Resource<ImageDetail>>,
    onProfileClick: (String) -> Unit,
    onDownloadImageClick: (String) -> Unit,
    onTagClick: (String) -> Unit,
    onSetWallpaperClick: (url: String) -> Unit,
    onLikeClicked: (ImageDetail) -> Unit,
    modifier: Modifier = Modifier,
) {
    val result: Resource<ImageDetail> by remember {
        imageDetail()
    }.collectAsState()

    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme().not()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BindTopImageHead(
            url = result.dataIfSuccess?.photo?.url.orEmpty(),
        )
        Spacer(modifier = Modifier.height(Dimen.smallest))
        ImageDetail(
            imageModel = result,
            onDownloadImageClick = onDownloadImageClick,
            onTagClick = onTagClick,
            onProfileClick = onProfileClick,
            onSetWallpaperClick = onSetWallpaperClick,
            onLikeClicked = onLikeClicked
        )
    }
}

@Composable
private fun ImageDetail(
    imageModel: Resource<ImageDetail>,
    onProfileClick: (String) -> Unit,
    onDownloadImageClick: (String) -> Unit,
    onTagClick: (String) -> Unit,
    onSetWallpaperClick: (url: String) -> Unit,
    onLikeClicked: (ImageDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserDetailImageHead(
            userUrl = imageModel.dataIfSuccess?.photo?.userUrl.orEmpty(),
            username = imageModel.dataIfSuccess?.photo?.username.orEmpty(),
            onProfileClick = remember(imageModel.dataIfSuccess?.photo?.username) {
                { onProfileClick(imageModel.dataIfSuccess?.photo?.username.orEmpty()) }
            },
            onDownloadImageClick = remember(imageModel.dataIfSuccess?.photo?.downloadUrl) {
                { onDownloadImageClick(imageModel.dataIfSuccess?.photo?.downloadUrl.orEmpty()) }
            },
            onSetWallpaperClick = remember(imageModel.dataIfSuccess?.photo?.downloadUrl) {
                { onSetWallpaperClick(imageModel.dataIfSuccess?.photo?.downloadUrl.orEmpty()) }
            },
            onLikeClicked = { imageModel.dataIfSuccess?.let(onLikeClicked) },
            isLiked = imageModel.dataIfSuccess?.isLiked ?: false
        )
        Divider(
            modifier = Modifier.padding(
                vertical = Dimen.medium,
            )
        )
        if (imageModel.dataIfSuccess?.photo?.tags.orEmpty().isNotEmpty()) {
            DetailImageBodyTags(
                tags = imageModel.dataIfSuccess?.photo?.tags.orEmpty(),
                onClick = onTagClick
            )
            Divider(
                modifier = Modifier.padding(
                    vertical = Dimen.medium,
                )
            )
        }
    }
}

@Composable
private fun UserDetailImageHead(
    userUrl: String,
    username: String,
    isLiked: Boolean,
    onDownloadImageClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSetWallpaperClick: () -> Unit,
    onLikeClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.small)
    ) {
        UserImageHeadWithUserName(
            modifier = Modifier.weight(1f),
            url = userUrl,
            username = username,
            onProfileClick = onProfileClick,
        )
        IconButton(
            onClick = onDownloadImageClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_download),
                contentDescription = null
            )
        }
        // TODO Add setting wallpaper
//        IconButton(
//            onClick = onSetWallpaperClick
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_baseline_wallpaper),
//                contentDescription = null
//            )
//        }
        IconButton(
            onClick = onLikeClicked
        ) {
            Icon(
                imageVector = if (isLiked) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder

                },
                contentDescription = null
            )
        }
    }
}

@Composable
private fun BindTopImageHead(
    url: String,
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    BackHandler(isExpanded) {
        isExpanded = isExpanded.not()
    }

    val height by animateDpAsState(
        targetValue = if (isExpanded) {
            LocalConfiguration.current.screenHeightDp.dp
        } else {
            300.dp
        },
        animationSpec = tween(600),
        label = "detailImageHeight"
    )

    ImageComponent(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clipToBounds()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                isExpanded = isExpanded.not()
            },
        url = url,
        contentScale = ContentScale.FillWidth
    )
}
