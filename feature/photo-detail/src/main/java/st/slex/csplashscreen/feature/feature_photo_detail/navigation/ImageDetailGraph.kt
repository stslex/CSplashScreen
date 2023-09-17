package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.feature_photo_detail.di.setupImageDetailComponent
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailScreen
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action

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

        val viewModel: ImageDetailViewModel = setupImageDetailComponent(arguments.imageId)

        LaunchedEffect(arguments) {
            viewModel.sendAction(Action.Init(arguments))
        }

        val state by remember {
            viewModel.state
        }.collectAsState()

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is ImageDetailStore.Event.Navigation -> viewModel.processNavigation(event)
            }
        }

        ImageDetailScreen(
            modifier = modifier,
            state = state,
            onProfileClick = remember {
                { username -> viewModel.sendAction(Action.OnProfileClick(username)) }
            },
            onDownloadImageClick = remember {
                { url -> viewModel.sendAction(Action.SetWallpaperClick(url)) }
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
    }
}