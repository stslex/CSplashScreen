package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationDestination {

    val destination: String
    val args: List<NamedNavArgument>

    object ImageDetailScreen : NavigationDestination {
        override val destination: String
            get() = "detail/{url}/{imageId}"
        override val args: List<NamedNavArgument>
            get() = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("imageId") { type = NavType.StringType }
            )
    }

    object MainScreen : NavigationDestination {
        override val destination: String
            get() = "mainScreen"
        override val args: List<NamedNavArgument>
            get() = emptyList()
    }

    object SingleCollectionScreen : NavigationDestination {
        override val destination: String
            get() = "collection/{collectionId}"
        override val args: List<NamedNavArgument>
            get() = listOf(navArgument("collectionId") { type = NavType.StringType })
    }

    object RawImageScreen : NavigationDestination {
        override val destination: String
            get() = "raw_image/{url}"
        override val args: List<NamedNavArgument>
            get() = listOf(navArgument("url") { type = NavType.StringType })
    }

    object SearchPhotosScreen : NavigationDestination {
        override val destination: String
            get() = "search_photos/{query}"
        override val args: List<NamedNavArgument>
            get() = listOf(navArgument("query") { type = NavType.StringType })
    }

    object UserScreen : NavigationDestination {
        override val destination: String
            get() = "user/{username}"
        override val args: List<NamedNavArgument>
            get() = listOf(navArgument("username") { type = NavType.StringType })
    }
}