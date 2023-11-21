package st.slex.csplashscreen.core.navigation

sealed class AppArguments(
    vararg val arguments: String
) {

    val argumentsForRoute: String
        get() = when (arguments.isEmpty()) {
            true -> String()
            false -> arguments.joinToString(separator = "/", prefix = "/")
        }

    data object Empty : AppArguments()

    data class ImageDetailScreen(
        val imageId: String
    ) : AppArguments(imageId)

    data class CollectionScreen(
        val collectionId: String
    ) : AppArguments(collectionId)

    data class SearchPhotosScreen(
        private val query: String
    ) : AppArguments(query) {

        val checkedQuery: String
            get() = arguments.firstOrNull().orEmpty().trimEnd()
    }

    data class UserScreen(
        val username: String
    ) : AppArguments(username)
}