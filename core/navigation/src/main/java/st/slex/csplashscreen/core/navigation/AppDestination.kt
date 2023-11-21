package st.slex.csplashscreen.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

enum class AppDestination(
    private vararg val argsNames: String
) {
    HOME,
    IMAGE_DETAIL("imageId"),
    COLLECTION("collection_id"),
    SEARCH_PHOTOS("query"),
    FAVOURITE,
    USER("username"),
    UNDEFINED;

    val destinationName: String = "${name}_route"
    val navigationRoute: String = "$destinationName${argsNames.argumentsRoute}"
    val composableArguments: List<NamedNavArgument> = argsNames.map { name ->
        navArgument(name) { NavType.StringType }
    }

    fun parseArguments(
        navBackStackEntry: NavBackStackEntry
    ): List<String> = argsNames.map { name ->
        navBackStackEntry.arguments?.getString(name).orEmpty()
    }

    private val Array<out String>.argumentsRoute: String
        get() = if (isEmpty()) "" else joinToString(
            separator = "}/{",
            prefix = "/{",
            postfix = "}"
        )

    companion object {

        fun findByRoute(
            route: String?
        ) = if (route.isNullOrBlank()) {
            UNDEFINED
        } else {
            AppDestination.entries.firstOrNull {
                it.navigationRoute == route
            }
        }
    }
}