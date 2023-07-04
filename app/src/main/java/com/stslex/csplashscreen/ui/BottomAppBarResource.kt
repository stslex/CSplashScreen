package com.stslex.csplashscreen.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.R

@Suppress("UNUSED")
enum class BottomAppBarResource(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String,
    val titleResource: Int,
    val screen: NavigationScreen
) {
    TOPICS(
        unselectedIcon = Icons.Outlined.Star,
        selectedIcon = Icons.Filled.Star,
        route = AppDestination.TOPICS.route,
        titleResource = R.string.nav_title_topics,
        screen = NavigationScreen.TopicsScreen(isLaunchSingle = true)
    ),
    HOME(
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = AppDestination.HOME.route,
        titleResource = R.string.nav_title_home,
        screen = NavigationScreen.Home(isLaunchSingle = true)
    ),
    SEARCH(
        unselectedIcon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        route = AppDestination.SEARCH_PHOTOS.route,
        titleResource = R.string.nav_title_search,
        screen = NavigationScreen.SearchPhotosScreen(query = " ", isLaunchSingle = true)
    )
}