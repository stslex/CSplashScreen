package st.slex.feature_main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import st.slex.core_navigation.AppDestinations
import st.slex.core_navigation.TopLevelDestination
import st.slex.feature_home.R
import st.slex.feature_main.ui.MainScreenRoute

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    composable(
        route = AppDestinations.Home.route
    ) {
        MainScreenRoute(
            modifier = modifier,
            onProfileClick = onProfileClick,
            onImageClick = onImageClick,
            onCollectionClick = onCollectionClick
        )
    }
}

val noteListTopLevelDestination = TopLevelDestination(
    route = AppDestinations.Home.route,
    selectedIcon = Icons.Filled.Home,
    unselectedIcon = Icons.Outlined.Home,
    iconTextId = R.string.home_tab_title
)
