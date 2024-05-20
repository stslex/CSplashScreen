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
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailScreen
import st.slex.csplashscreen.feature.feature_photo_detail.ui.components.dialogs.DownloadImageDialog
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStoreComponent.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStoreComponent.DialogType
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStoreComponent.Event

fun NavGraphBuilder.imageDetailGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(AppDestination.IMAGE_DETAIL) { store: ImageDetailStore, args ->
        val arguments = args.firstOrNull()
            .orEmpty()
            .let(AppArguments::ImageDetailScreen)

        LaunchedEffect(arguments) {
            store.sendAction(Action.Init(arguments))
        }

        val state by remember { store.state }.collectAsState()

        var dialogType by remember {
            mutableStateOf(DialogType.NONE)
        }

        val blurBackground by animateDpAsState(
            targetValue = if (dialogType == DialogType.NONE) 0.dp else 16.dp,
            animationSpec = tween(durationMillis = 600),
            label = "anim_blur_dialog_background"
        )

        store.event.CollectAsEvent { event ->
            when (event) {
                is Event.Dialog -> dialogType = event.type
            }
        }

        ImageDetailScreen(
            modifier = modifier.blur(blurBackground),
            state = state,
            onProfileClick = remember {
                { username -> store.sendAction(Action.OnProfileClick(username)) }
            },
            onDownloadImageClick = remember {
                { store.sendAction(Action.DownloadImageButtonClick) }
            },
            onTagClick = remember {
                { tag -> store.sendAction(Action.OnTagClick(tag)) }
            },
            onSetWallpaperClick = remember {
                { url -> store.sendAction(Action.SetWallpaperClick(url)) }
            },
            onLikeClicked = remember {
                { image -> store.sendAction(Action.OnLikeClicked(image)) }
            }
        )

        if (dialogType != DialogType.NONE) {
            Dialog(
                onDismissRequest = {
                    store.sendAction(Action.CloseDialog)
                },
            ) {
                when (dialogType) {
                    DialogType.DOWNLOAD -> DownloadImageDialog(store::sendAction)
                    DialogType.NONE -> Unit
                }
            }
        }
    }
}
