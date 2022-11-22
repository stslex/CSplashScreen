package st.slex.feature_collection.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_navigation.testing.AppDestination
import st.slex.core_navigation.utils.NavExt.composableArguments
import st.slex.core_navigation.utils.NavExt.parseArguments
import st.slex.feature_collection.ui.CollectionScreen

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier
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
            viewModel = koinViewModel { parametersOf(arguments) }
        )
    }
}