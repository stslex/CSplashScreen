package st.slex.feature_main.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.core_navigation.testing.NavigationScreen
import st.slex.feature_main.ui.MainScreenRoute

fun NavGraphBuilder.homeGraph(modifier: Modifier = Modifier) {
    composable(route = NavigationScreen.Home.screenRoute) {
        MainScreenRoute(modifier = modifier)
    }
}
