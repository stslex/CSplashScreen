package st.slex.feature_user.navigation

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
import st.slex.feature_user.ui.UserScreen

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.USER.navigationRoute,
        arguments = AppDestination.USER.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.USER.parseArguments(navBackStackEntry).let { args ->
            AppArguments.UserScreen(args.first())
        }
        UserScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}