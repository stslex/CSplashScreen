package com.stslex.csplashscreen.core.navigation

sealed class NavigationScreen {

    abstract val screen: AppDestination
    abstract val appArgs: AppArguments

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    open val isSingleTop: Boolean
        get() = false

    data class Home(
        private val isLaunchSingle: Boolean = true
    ) : NavigationScreen() {
        override val screen: AppDestination
            get() = AppDestination.HOME
        override val appArgs: AppArguments.Empty
            get() = AppArguments.Empty
        override val isSingleTop: Boolean
            get() = isLaunchSingle
    }

    data class ImageDetailScreen(
        private val imageId: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.IMAGE_DETAIL

        override val appArgs: AppArguments.ImageDetailScreen
            get() = AppArguments.ImageDetailScreen(imageId)
    }

    data class CollectionScreen(
        private val collectionId: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.COLLECTION

        override val appArgs: AppArguments.CollectionScreen
            get() = AppArguments.CollectionScreen(collectionId)
    }

    data class SearchPhotosScreen(
        private val query: String,
        private val isLaunchSingle: Boolean = true
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.SEARCH_PHOTOS

        override val appArgs: AppArguments.SearchPhotosScreen
            get() = AppArguments.SearchPhotosScreen(query)

        override val isSingleTop: Boolean
            get() = isLaunchSingle
    }

    data class UserScreen(
        private val username: String
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.USER

        override val appArgs: AppArguments.UserScreen
            get() = AppArguments.UserScreen(username)
    }

    data class TopicsScreen(
        private val isLaunchSingle: Boolean = true
    ) : NavigationScreen() {

        override val screen: AppDestination
            get() = AppDestination.TOPICS

        override val appArgs: AppArguments.Empty
            get() = AppArguments.Empty

        override val isSingleTop: Boolean
            get() = isLaunchSingle
    }

    object PopBackStack : NavigationScreen() {
        override val screen: AppDestination
            get() = throw Exception("PopBackStack")
        override val appArgs: AppArguments
            get() = throw Exception("PopBackStack")
    }
}