package com.stslex.csplashscreen.core.navigation

sealed class NavigationScreen {

    abstract val screen: AppDestination

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    open val isSingleTop: Boolean
        get() = false

    open val appArgs: AppArguments
        get() = AppArguments.Empty

    object Home : NavigationScreen() {

        override val screen: AppDestination = AppDestination.HOME
        override val isSingleTop: Boolean = true
    }

    data class ImageDetailScreen(
        private val imageId: String
    ) : NavigationScreen() {

        override val screen: AppDestination = AppDestination.IMAGE_DETAIL
        override val appArgs: AppArguments.ImageDetailScreen =
            AppArguments.ImageDetailScreen(imageId)
    }

    data class CollectionScreen(
        private val collectionId: String
    ) : NavigationScreen() {

        override val screen: AppDestination = AppDestination.COLLECTION
        override val appArgs: AppArguments = AppArguments.CollectionScreen(collectionId)
    }

    data class SearchPhotosScreen(
        private val query: String,
    ) : NavigationScreen() {

        override val screen: AppDestination = AppDestination.SEARCH_PHOTOS
        override val appArgs: AppArguments = AppArguments.SearchPhotosScreen(query)
        override val isSingleTop: Boolean = true
    }

    data class UserScreen(
        private val username: String
    ) : NavigationScreen() {

        override val screen: AppDestination = AppDestination.USER
        override val appArgs: AppArguments = AppArguments.UserScreen(username)
    }

    object Favourite : NavigationScreen() {

        override val screen: AppDestination = AppDestination.FAVOURITE
        override val appArgs: AppArguments = AppArguments.Empty
        override val isSingleTop: Boolean = true
    }

    object PopBackStack : NavigationScreen() {
        override val screen: AppDestination = throw Exception("PopBackStack")
        override val appArgs: AppArguments = throw Exception("PopBackStack")
    }
}