package st.slex.csplashscreen.core.ui.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import st.slex.csplashscreen.core.navigation.AppDestination

inline fun <reified VM : ViewModel> NavGraphBuilder.createScreen(
    appDestination: AppDestination,
    crossinline content: @Composable (VM, List<String>) -> Unit
) {
    composable(
        route = appDestination.navigationRoute,
        arguments = appDestination.composableArguments
    ) { navBackStackEntry ->
        val arguments = appDestination.parseArguments(navBackStackEntry)
        val viewModel: VM = koinViewModel(
            key = arguments.hashCode().toString()
        )
        /*TODO maybe good point to make instance of state, event, action here
           and then send in to Content Screen*/
        content(viewModel, arguments)
    }
}