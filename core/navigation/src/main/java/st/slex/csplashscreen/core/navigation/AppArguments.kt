package st.slex.csplashscreen.core.navigation

sealed class AppArguments {

    abstract val arguments: List<String>

    open val argumentsForRoute: String
        get() = arguments.joinToString(separator = "/", prefix = "/")

    object Empty : AppArguments() {
        override val arguments: List<String>
            get() = emptyList()
        override val argumentsForRoute: String
            get() = String()
    }

    data class ImageDetailScreen(
        val imageId: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(imageId)
    }

    data class CollectionScreen(
        val collectionId: String
    ) : AppArguments() {

        override val arguments: List<String>
            get() = listOf(collectionId)
    }

    data class SearchPhotosScreen(
        private val query: String
    ) : AppArguments() {

        override val arguments: List<String>
            get() = listOf(query)

        val checkedQuery: String
            get() = arguments.firstOrNull().orEmpty().trimEnd()
    }

    data class UserScreen(
        val username: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(username)
    }
}