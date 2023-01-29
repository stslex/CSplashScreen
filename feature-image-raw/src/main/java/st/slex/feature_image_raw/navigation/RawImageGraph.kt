package st.slex.feature_image_raw.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.AppArguments
import st.slex.core_navigation.AppDestination
import st.slex.core_navigation.NavigationScreen
import st.slex.core_navigation.NavExt.composableArguments
import st.slex.core_navigation.NavExt.parseArguments
import st.slex.feature_image_raw.ui.RawImageScreen

fun NavGraphBuilder.rawImageGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.RAW_IMAGE.navigationRoute,
        arguments = AppDestination.RAW_IMAGE.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.RAW_IMAGE.parseArguments(navBackStackEntry).let { args ->
            AppArguments.RawImageScreen(args.first())
        }
        RawImageScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}