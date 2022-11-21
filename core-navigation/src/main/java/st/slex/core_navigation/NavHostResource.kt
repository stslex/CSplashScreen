package st.slex.core_navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavHostResource {

    val destination: String
    val arguments: List<String>
        get() = emptyList()

    val args: List<NamedNavArgument>
        get() = emptyList()

    val NavBackStackEntry.convertArgs: List<String>
        get() = this@NavHostResource.arguments.map { arguments?.getString(it).orEmpty() }

    val convertRoute: String
        get() = "$destination${arguments.convertHostArgumentsRoute()}"

    private fun List<String>.convertHostArgumentsRoute() = if (!isNullOrEmpty()) {
        joinToString(separator = "}/{", prefix = "/{", postfix = "}")
    } else String()

    object ImageDetailScreen : NavHostResource {
        override val destination: String = "detail"
        override val arguments: List<String> = listOf("url", "imageId")
        override val args: List<NamedNavArgument> = listOf(
            navArgument(arguments[0]) { type = NavType.StringType },
            navArgument(arguments[1]) { type = NavType.StringType }
        )
    }

    object CollectionScreen : NavHostResource {
        override val destination: String = "collection"
        override val arguments: List<String> = listOf("collectionId")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object RawImageScreen : NavHostResource {
        override val destination: String = "raw_image"
        override val arguments: List<String> = listOf("url")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object SearchPhotosScreen : NavHostResource {
        override val destination: String = "search"
        override val arguments: List<String> = listOf("query")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object UserScreen : NavHostResource {
        override val destination: String = "user"
        override val arguments: List<String> = listOf("username")
        override val args: List<NamedNavArgument> =
            listOf(navArgument(arguments[0]) { type = NavType.StringType })
    }

    object TopicsScreen : NavHostResource {
        override val destination: String = "topics"
    }
}