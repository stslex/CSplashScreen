package st.slex.feature_photo_detail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.navigation.NavExt.composableArguments
import com.stslex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.feature_photo_detail.ui.ImageDetailScreen

fun NavGraphBuilder.imageDetailGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.IMAGE_DETAIL.navigationRoute,
        arguments = AppDestination.IMAGE_DETAIL.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.IMAGE_DETAIL.parseArguments(navBackStackEntry).let { args ->
            AppArguments.ImageDetailScreen(args[0], args[1])
        }
        ImageDetailScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}