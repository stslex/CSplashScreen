package st.slex.core_navigation

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
        val url: String,
        val imageId: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(url, imageId)
    }

    data class CollectionScreen(
        val collectionId: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(collectionId)
    }

    data class RawImageScreen(
        val url: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(url)
    }

    data class SearchPhotosScreen(
        val query: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(query)

        val checkedQuery: String
            get() = query.ifBlank { String() }
    }

    data class UserScreen(
        val username: String
    ) : AppArguments() {
        override val arguments: List<String>
            get() = listOf(username)
    }
}