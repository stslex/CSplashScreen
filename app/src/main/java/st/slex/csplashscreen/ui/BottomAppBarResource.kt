package st.slex.csplashscreen.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import st.slex.csplashscreen.ui.navigation.NavHostResource


sealed interface BottomAppBarResource {

    val destination: String
    val icon: ImageVector
    val route: String
        get() = destination

    object MainScreen : BottomAppBarResource {
        override val destination: String = NavHostResource.MainScreen.destination
        override val icon: ImageVector = Icons.Outlined.Home
    }

    object TopicsScreen : BottomAppBarResource {
        override val destination: String = NavHostResource.TopicsScreen.destination
        override val icon: ImageVector = Icons.Outlined.Star
    }

    object SearchScreen : BottomAppBarResource {
        override val destination: String = NavHostResource.SearchPhotosScreen.destination
        override val icon: ImageVector = Icons.Outlined.Search
        override val route: String = "$destination/ "
    }
}