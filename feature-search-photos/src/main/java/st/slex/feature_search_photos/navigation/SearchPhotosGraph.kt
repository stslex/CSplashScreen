package st.slex.feature_search_photos.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_navigation.testing.AppDestination
import st.slex.core_navigation.utils.NavExt.composableArguments
import st.slex.core_navigation.utils.NavExt.parseArguments
import st.slex.feature_search_photos.ui.SearchPhotosScreen

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier
) {
    composable(
        route = AppDestination.SEARCH_PHOTOS.navigationRoute,
        arguments = AppDestination.SEARCH_PHOTOS.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.SEARCH_PHOTOS.parseArguments(navBackStackEntry).let { args ->
            AppArguments.SearchPhotosScreen(args[0])
        }
        SearchPhotosScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments) }
        )
    }
}