package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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

        val viewModel: ImageDetailViewModel = koinViewModel(
            key = arguments.imageId
        ) { parametersOf(arguments) }


        ImageDetailScreen(
            modifier = modifier,
            imageDetail = viewModel::imageDetail,
            onProfileClick = viewModel::onProfileClick,
            onDownloadImageClick = viewModel::onDownloadImageClick,
            onTagClick = viewModel::onTagClick,
            onSetWallpaperClick = viewModel::onWallpaperSetClick,
            onLikeClicked = viewModel::onLikeClick
        )
    }
}