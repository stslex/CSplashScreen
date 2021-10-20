package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NavOptions

sealed interface NavActions {

    val destination: String
        get() = ""
    val arguments: Map<String, String>
        get() = emptyMap()
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()

    data class ImageDetailScreen(val url: String, val imageId: String) : NavActions {
        override val destination: String = "detail"
        override val arguments: Map<String, String>
            get() = mapOf("url" to url, "imageId" to imageId)
    }

    object MainScreen : NavActions {
        override val destination: String = "mainScreen"
    }

    data class SingleCollectionScreen(val collectionId: String) : NavActions {
        override val destination: String = "collection"
        override val arguments: Map<String, String>
            get() = mapOf("collectionId" to collectionId)
    }

    data class RawImageScreen(val url: String) : NavActions {
        override val destination: String = "raw_image"
        override val arguments: Map<String, String>
            get() = mapOf("url" to url)
    }

    data class SearchPhotosScreen(val query: String) : NavActions {
        override val destination: String = "search"
        override val arguments: Map<String, String>
            get() = mapOf("query" to query)
    }

    data class UserScreen(val username: String) : NavActions {
        override val destination: String = "user"
        override val arguments: Map<String, String>
            get() = mapOf("username" to username)
    }

    object TopicsScreen : NavActions {
        override val destination: String = "topics"
    }
}