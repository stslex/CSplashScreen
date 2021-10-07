package st.slex.csplashscreen.ui.navigation

//TODO inject this in navigation destination
sealed interface NavigationState {

    val destination: String

    object ImageDetailScreen : NavigationState {
        override val destination: String = "detail"
    }

    object MainScreen : NavigationState {
        override val destination: String = "mainScreen"
    }

    object SingleCollectionScreen : NavigationState {
        override val destination: String = "collection"
    }

    object RawImageScreen : NavigationState {
        override val destination: String = "raw_image"
    }

    object SearchPhotosScreen : NavigationState {
        override val destination: String = "search_photos"
    }

    object UserScreen : NavigationState {
        override val destination: String = "user"
    }
}