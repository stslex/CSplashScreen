package st.slex.core_navigation.testing

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
        private val url: String,
        private val imageId: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(url, imageId)
    }

    data class CollectionScreen(
        private val collectionId: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(collectionId)
    }

    data class RawImageScreen(
        private val url: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(url)
    }

    data class SearchPhotosScreen(
        private val query: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(query)
    }

    data class UserScreen(
        private val username: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(username)
    }
}