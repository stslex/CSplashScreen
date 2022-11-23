package st.slex.feature_image_raw.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_navigation.testing.AppDestination
import st.slex.core_navigation.utils.NavExt.composableArguments
import st.slex.core_navigation.utils.NavExt.parseArguments
import st.slex.feature_image_raw.ui.RawImageScreen

fun NavGraphBuilder.rawImageGraph(
    modifier: Modifier = Modifier
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
            viewModel = koinViewModel { parametersOf(arguments) }
        )
    }
}