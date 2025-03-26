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
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.navigation.Screen.Favourite
import st.slex.csplashscreen.core.navigation.Screen.Home
import st.slex.csplashscreen.core.navigation.Screen.SearchPhotosScreen

enum class BottomAppBarResource(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val titleResource: Int,
    val screen: Screen
) {
    FAVOURITE(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        titleResource = R.string.nav_title_favourite,
        screen = Favourite
    ),
    HOME(
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        titleResource = R.string.nav_title_home,
        screen = Home
    ),
    SEARCH(
        unselectedIcon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        titleResource = R.string.nav_title_search,
        screen = SearchPhotosScreen(query = " ")
    );

    fun getIcon(isSelected: Boolean) = if (isSelected) selectedIcon else unselectedIcon

    companion object {

        fun Screen?.isAppbar(): Boolean = entries.any { it.screen == this }

        fun getByRoute(
            route: String
        ): Screen? = BottomAppBarResource.entries.find { entry ->
            route.startsWith(checkNotNull(entry.screen.javaClass.canonicalName))
        }?.screen
    }
}