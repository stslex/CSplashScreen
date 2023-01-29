package st.slex.feature_main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.core_navigation.AppDestination
import st.slex.core_navigation.NavigationScreen
import st.slex.feature_main.ui.main.MainScreenRoute

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(route = AppDestination.HOME.navigationRoute) {
        MainScreenRoute(modifier = modifier, navigator = navigate)
    }
}
