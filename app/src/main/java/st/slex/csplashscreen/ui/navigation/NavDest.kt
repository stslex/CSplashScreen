package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDest {

    val route: String
    val args: List<NamedNavArgument>
    val destination: String

    object ImageDetailScreen : NavDest {
        override val route: String = "detail/{url}/{imageId}"
        override val args: List<NamedNavArgument> = listOf(
            navArgument("url") { type = NavType.StringType },
            navArgument("imageId") { type = NavType.StringType }
        )
        override val destination: String = "detail"
    }

    object MainScreen : NavDest {
        override val route: String = "mainScreen"
        override val args: List<NamedNavArgument> = emptyList()
        override val destination: String = "mainScreen"
    }

    object SingleCollectionScreen : NavDest {
        override val route: String = "collection/{collectionId}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("collectionId") { type = NavType.StringType })
        override val destination: String = "collection"
    }

    object RawImageScreen : NavDest {
        override val route: String = "raw_image/{url}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("url") { type = NavType.StringType })
        override val destination: String = "raw_image"
    }

    object SearchPhotosScreen : NavDest {
        override val route: String = "search_photos/{query}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("query") { type = NavType.StringType })
        override val destination: String = "search_photos"
    }

    object UserScreen : NavDest {
        override val route: String = "user/{username}"
        override val args: List<NamedNavArgument> =
            listOf(navArgument("username") { type = NavType.StringType })
        override val destination: String = "user"
    }
}