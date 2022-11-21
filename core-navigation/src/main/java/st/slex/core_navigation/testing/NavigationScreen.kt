package st.slex.core_navigation.testing

sealed class NavigationScreen {

    abstract val screen: AppDestination
    abstract val appArgs: AppArguments

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    object Home : NavigationScreen() {
        override val screen: AppDestination
            get() = AppDestination.HOME
        override val appArgs: AppArguments.Empty
            get() = AppArguments.Empty
    }

    data class ImageDetailScreen(
        private val url: String,
        private val imageId: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.IMAGE_DETAIL

        override val appArgs: AppArguments.ImageDetailScreen
            get() = AppArguments.ImageDetailScreen(url, imageId)
    }

    data class CollectionScreen(
        private val collectionId: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.COLLECTION

        override val appArgs: AppArguments.CollectionScreen
            get() = AppArguments.CollectionScreen(collectionId)
    }

    data class RawImageScreen(
        private val url: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.RAW_IMAGE

        override val appArgs: AppArguments.RawImageScreen
            get() = AppArguments.RawImageScreen(url)
    }

    data class SearchPhotosScreen(
        private val query: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.SEARCH_PHOTOS

        override val appArgs: AppArguments.SearchPhotosScreen
            get() = AppArguments.SearchPhotosScreen(query)
    }

    data class UserScreen(
        private val username: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.USER

        override val appArgs: AppArguments.UserScreen
            get() = AppArguments.UserScreen(username)
    }

    object TopicsScreen : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.TOPICS

        override val appArgs: AppArguments.Empty
            get() = AppArguments.Empty
    }
}