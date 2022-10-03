package st.slex.csplashscreen.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import st.slex.core_navigation.AppDestinations
import st.slex.core_navigation.NavHostResource


sealed interface BottomAppBarResource {

    val destination: String
    val unselectedIcon: ImageVector
    val selectedIcon: ImageVector
    val route: String
        get() = destination
    val title: String
        get() = route

    object MainScreen : BottomAppBarResource {

        override val destination: String
            get() = AppDestinations.Home.route

        override val unselectedIcon: ImageVector
            get() = Icons.Outlined.Home

        override val selectedIcon: ImageVector
            get() = Icons.Filled.Home

        override val title: String
            get() = "home"
    }

    object TopicsScreen : BottomAppBarResource {

        override val destination: String
            get() = NavHostResource.TopicsScreen.destination

        override val unselectedIcon: ImageVector
            get() = Icons.Outlined.Star

        override val selectedIcon: ImageVector
            get() = Icons.Filled.Star
    }

    object SearchScreen : BottomAppBarResource {

        override val destination: String
            get() = NavHostResource.SearchPhotosScreen.destination

        override val route: String
            get() = "$destination/ "

        override val unselectedIcon: ImageVector
            get() = Icons.Outlined.Search

        override val selectedIcon: ImageVector
            get() = Icons.Filled.Search
    }
}