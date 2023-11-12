package st.slex.csplashscreen.ui.components.bottom_appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import st.slex.csplashscreen.R
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavigationScreen

enum class BottomAppBarResource(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val appDestination: AppDestination,
    val titleResource: Int,
    val screen: NavigationScreen
) {
    FAVOURITE(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        appDestination = AppDestination.FAVOURITE,
        titleResource = R.string.nav_title_favourite,
        screen = NavigationScreen.Favourite
    ),
    HOME(
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        appDestination = AppDestination.HOME,
        titleResource = R.string.nav_title_home,
        screen = NavigationScreen.Home
    ),
    SEARCH(
        unselectedIcon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        appDestination = AppDestination.SEARCH_PHOTOS,
        titleResource = R.string.nav_title_search,
        screen = NavigationScreen.SearchPhotosScreen(query = " ")
    );

    fun getIcon(isSelected: Boolean) = if (isSelected) selectedIcon else unselectedIcon

    companion object {

        fun isAppbar(
            appDestination: AppDestination?
        ): Boolean = entries.any { it.appDestination == appDestination }
    }
}