package st.slex.feature_main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.core_navigation.testing.AppDestination
import st.slex.feature_main.ui.main.MainScreenRoute

fun NavGraphBuilder.homeGraph(modifier: Modifier = Modifier) {
    composable(route = AppDestination.HOME.navigationRoute) {
        MainScreenRoute(modifier = modifier)
    }
}
