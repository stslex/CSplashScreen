package st.slex.feature_user.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_navigation.testing.AppDestination
import st.slex.core_navigation.utils.NavExt.composableArguments
import st.slex.core_navigation.utils.NavExt.parseArguments
import st.slex.feature_user.ui.UserScreen

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier
) {
    composable(
        route = AppDestination.USER.navigationRoute,
        arguments = AppDestination.USER.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.USER.parseArguments(navBackStackEntry).let { args ->
            AppArguments.SearchPhotosScreen(args[0])
        }
        UserScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments) }
        )
    }
}