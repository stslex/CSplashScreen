package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationDestination {

    val destination: String
    val args: List<NamedNavArgument>

    object ImageDetailScreen : NavigationDestination {
        override val destination: String = "detail/{url}/{imageId}"
        override val args: List<NamedNavArgument> = listOf(
            navArgument("url") { type = NavType.StringType },
            navArgument("imageId") { type = NavType.StringType }
        )
    }

    object MainScreen : NavigationDestination {
        override val destination: String = "mainScreen"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object SingleCollectionScreen : NavigationDestination {
        override val destination: String = "collection/{collectionId}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("collectionId") { type = NavType.StringType })
    }

    object RawImageScreen : NavigationDestination {
        override val destination: String = "raw_image/{url}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("url") { type = NavType.StringType })
    }

    object SearchPhotosScreen : NavigationDestination {
        override val destination: String = "search_photos/{query}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("query") { type = NavType.StringType })
    }

    object UserScreen : NavigationDestination {
        override val destination: String = "user/{username}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("username") { type = NavType.StringType })
    }
}