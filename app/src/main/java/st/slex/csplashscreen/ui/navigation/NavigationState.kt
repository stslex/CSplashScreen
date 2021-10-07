package st.slex.csplashscreen.ui.navigation

sealed interface NavigationState {

    object Initial : NavigationState

    object ImageDetailScreen : NavigationState

    object MainScreen : NavigationState

    object SingleCollectionScreen : NavigationState

    object RawImageScreen : NavigationState

    object SearchPhotosScreen : NavigationState

    object UserScreen : NavigationState
}