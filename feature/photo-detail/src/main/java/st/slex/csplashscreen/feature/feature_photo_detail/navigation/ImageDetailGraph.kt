package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.core.ui.base.setupComponent
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.feature_photo_detail.di.ImageDetailComponentBuilder
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailScreen
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.DialogType
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.ui.components.dialogs.DownloadImageDialog

fun NavGraphBuilder.imageDetailGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.IMAGE_DETAIL.navigationRoute,
        arguments = AppDestination.IMAGE_DETAIL.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.IMAGE_DETAIL.parseArguments(navBackStackEntry).let { args ->
            AppArguments.ImageDetailScreen(args[0])
        }

        val viewModel: ImageDetailViewModel = setupComponent(
            key = arguments.imageId,
            builder = ImageDetailComponentBuilder
        )

        LaunchedEffect(arguments) {
            viewModel.sendAction(Action.Init(arguments))
        }

        val state by remember {
            viewModel.state
        }.collectAsState()

        var dialogType by remember {
            mutableStateOf(DialogType.NONE)
        }

        val blurBackground by animateDpAsState(
            targetValue = if (dialogType == DialogType.NONE) 0.dp else 16.dp,
            animationSpec = tween(durationMillis = 600)
        )

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is Event.Dialog -> dialogType = event.type
            }
        }

        ImageDetailScreen(
            modifier = modifier.blur(blurBackground),
            state = state,
            onProfileClick = remember {
                { username -> viewModel.sendAction(Action.OnProfileClick(username)) }
            },
            onDownloadImageClick = remember {
                { viewModel.sendAction(Action.DownloadImageButtonClick) }
            },
            onTagClick = remember {
                { tag -> viewModel.sendAction(Action.OnTagClick(tag)) }
            },
            onSetWallpaperClick = remember {
                { url -> viewModel.sendAction(Action.SetWallpaperClick(url)) }
            },
            onLikeClicked = remember {
                { image -> viewModel.sendAction(Action.OnLikeClicked(image)) }
            }
        )

        if (dialogType != DialogType.NONE) {
            Dialog(
                onDismissRequest = {
                    viewModel.sendAction(Action.CloseDialog)
                },
            ) {
                when (dialogType) {
                    DialogType.DOWNLOAD -> DownloadImageDialog(viewModel::sendAction)
                    DialogType.NONE -> Unit
                }
            }
        }
    }
}
