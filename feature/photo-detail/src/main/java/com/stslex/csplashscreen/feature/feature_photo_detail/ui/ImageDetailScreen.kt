package com.stslex.csplashscreen.feature.feature_photo_detail.ui

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stslex.csplashscreen.core.core.Resource
import com.stslex.csplashscreen.core.ui.components.ImageComponent
import com.stslex.csplashscreen.core.ui.components.UserImageHeadWithUserName
import com.stslex.csplashscreen.core.ui.theme.Dimen
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.components.DetailImageBodyTags
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.components.DownloadImageButton
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.components.WallPaperButton
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ImageDetailScreen(
    imageDetail: () -> StateFlow<Resource<ImageDetail>>,
    onProfileClick: (String) -> Unit,
    onDownloadImageClick: (String) -> Unit,
    onTagClick: (String) -> Unit,
    onSetWallpaperClick: (url: String) -> Unit,
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
            url = result.dataIfSuccess?.url.orEmpty(),
        )
        Spacer(modifier = Modifier.height(Dimen.smallest))
        ImageDetail(
            imageModel = result,
            onDownloadImageClick = onDownloadImageClick,
            onTagClick = onTagClick,
            onProfileClick = onProfileClick,
            onSetWallpaperClick = onSetWallpaperClick
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserDetailImageHead(
            userUrl = imageModel.dataIfSuccess?.userUrl.orEmpty(),
            username = imageModel.dataIfSuccess?.username.orEmpty(),
            onProfileClick = remember(imageModel.dataIfSuccess?.username) {
                { onProfileClick(imageModel.dataIfSuccess?.username.orEmpty()) }
            },
            onDownloadImageClick = remember(imageModel.dataIfSuccess?.downloadUrl) {
                { onDownloadImageClick(imageModel.dataIfSuccess?.downloadUrl.orEmpty()) }
            },
            onSetWallpaperClick = remember(imageModel.dataIfSuccess?.downloadUrl) {
                { onSetWallpaperClick(imageModel.dataIfSuccess?.downloadUrl.orEmpty()) }
            }
        )
        Divider(
            modifier = Modifier.padding(
                vertical = Dimen.medium,
            )
        )
        if (imageModel.dataIfSuccess?.tags.orEmpty().isNotEmpty()) {
            DetailImageBodyTags(
                tags = imageModel.dataIfSuccess?.tags.orEmpty(),
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
    onDownloadImageClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSetWallpaperClick: () -> Unit
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
        DownloadImageButton(
            modifier = Modifier,
            onDownloadImageClick = onDownloadImageClick
        )
        WallPaperButton(
            modifier = Modifier,
            onSetWallpaperClick = onSetWallpaperClick
        )
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
