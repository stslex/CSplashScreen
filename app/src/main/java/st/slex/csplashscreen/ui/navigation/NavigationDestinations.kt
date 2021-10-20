package st.slex.csplashscreen.ui.navigation

sealed interface NavigationDestinations {

    val rawDestinations: String
    val arguments: List<String>
    val destinations: String

    data class ImageDetailScreen(val url: String, val id: String) : NavigationDestinations {
        override val rawDestinations: String = "detail"
        override val arguments: List<String> = listOf(url, id)
        override val destinations: String = convertRoute()
    }

    object MainScreen : NavigationDestinations {
        override val rawDestinations: String = "home"
        override val arguments: List<String> = emptyList()
        override val destinations: String = convertRoute()
    }

    data class SingleCollectionScreen(val id: String) : NavigationDestinations {
        override val rawDestinations: String = "collection"
        override val arguments: List<String> = listOf(id)
        override val destinations: String = convertRoute()
    }

    data class RawImageScreen(val url: String) : NavigationDestinations {
        override val rawDestinations: String = "raw_image"
        override val arguments: List<String> = listOf(url)
        override val destinations: String = convertRoute()
    }

    data class SearchPhotosScreen(val query: String) : NavigationDestinations {
        override val rawDestinations: String = "search"
        override val arguments: List<String> = listOf(query)
        override val destinations: String = convertRoute()
    }

    data class UserScreen(val username: String) : NavigationDestinations {
        override val rawDestinations: String = "user"
        override val arguments: List<String> = listOf(username)
        override val destinations: String = convertRoute()
    }

    object TopicsScreen : NavigationDestinations {
        override val rawDestinations: String = "topics"
        override val arguments: List<String> = emptyList()
        override val destinations: String = convertRoute()
    }

    fun NavigationDestinations.convertRoute() =
        "$rawDestinations${arguments.convertArgumentsRoute()}"

    fun List<String>.convertArgumentsRoute() = if (!isNullOrEmpty()) {
        joinToString(separator = "/", prefix = "/")
    } else ""
}