package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationResource {

    val destination: String
    val arguments: List<String>
        get() = emptyList()
    val args: List<NamedNavArgument>
        get() = emptyList()

    object ImageDetailScreen : NavigationResource {
        override val destination: String = "detail"
        override val arguments: List<String> = listOf("url", "imageId")
        override val args: List<NamedNavArgument> = listOf(
            navArgument(arguments[0]) { type = NavType.StringType },
            navArgument(arguments[1]) { type = NavType.StringType }
        )
    }

    object MainScreen : NavigationResource {
        override val destination: String = "home"
    }

    object SingleCollectionScreen : NavigationResource {
        override val destination: String = "collection"
        override val arguments: List<String> = listOf("collectionId")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object RawImageScreen : NavigationResource {
        override val destination: String = "raw_image"
        override val arguments: List<String> = listOf("url")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object SearchPhotosScreen : NavigationResource {
        override val destination: String = "search"
        override val arguments: List<String> = listOf("query")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object UserScreen : NavigationResource {
        override val destination: String = "user"
        override val arguments: List<String> = listOf("username")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object TopicsScreen : NavigationResource {
        override val destination: String = "topics"
    }
}