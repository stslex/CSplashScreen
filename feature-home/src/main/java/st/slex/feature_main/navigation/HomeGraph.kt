package st.slex.feature_main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.core_navigation.AppDestinations
import st.slex.feature_main.ui.MainScreenRoute

fun NavGraphBuilder.homeGraph(modifier: Modifier = Modifier) {
    composable(route = AppDestinations.Home.route) {
        MainScreenRoute(modifier = modifier)
    }
}
