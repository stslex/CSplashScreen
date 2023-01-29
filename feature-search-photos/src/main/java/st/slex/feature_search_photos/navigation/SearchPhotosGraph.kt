package st.slex.feature_search_photos.navigation

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
import st.slex.feature_search_photos.ui.SearchPhotosScreen

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
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
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}