package st.slex.feature_collection.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.AppArguments
import st.slex.core_navigation.AppDestination
import st.slex.core_navigation.NavExt.composableArguments
import st.slex.core_navigation.NavExt.parseArguments
import st.slex.core_navigation.NavigationScreen
import st.slex.feature_collection.ui.CollectionScreen

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.COLLECTION.navigationRoute,
        arguments = AppDestination.COLLECTION.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.COLLECTION.parseArguments(navBackStackEntry).let { args ->
            AppArguments.CollectionScreen(args[0])
        }
        CollectionScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}