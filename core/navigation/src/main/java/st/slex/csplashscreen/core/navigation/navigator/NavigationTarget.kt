package st.slex.csplashscreen.core.navigation.navigator

import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination

sealed interface NavigationTarget {

    data object PopBackStack : NavigationTarget

    sealed interface Screen : NavigationTarget, NavigationScreen {

        val screenRoute: String
            get() = "${screen.destinationName}${appArgs.argumentsForRoute}"

        data object Home : Screen {
            override val screen: AppDestination = AppDestination.HOME
            override val isSingleTop: Boolean = true
        }

        data class ImageDetailScreen(
            private val imageId: String
        ) : Screen {
            override val screen: AppDestination = AppDestination.IMAGE_DETAIL
            override val appArgs: AppArguments.ImageDetailScreen =
                AppArguments.ImageDetailScreen(imageId)
        }

        data class CollectionScreen(
            private val collectionId: String
        ) : Screen {
            override val screen: AppDestination = AppDestination.COLLECTION
            override val appArgs: AppArguments = AppArguments.CollectionScreen(collectionId)
        }

        data class SearchPhotosScreen(
            private val query: String,
        ) : Screen {
            override val screen: AppDestination = AppDestination.SEARCH_PHOTOS
            override val appArgs: AppArguments = AppArguments.SearchPhotosScreen(query)
            override val isSingleTop: Boolean = true
        }

        data class UserScreen(
            private val username: String
        ) : Screen {
            override val screen: AppDestination = AppDestination.USER
            override val appArgs: AppArguments = AppArguments.UserScreen(username)
        }

        data object Favourite : Screen {
            override val screen: AppDestination = AppDestination.FAVOURITE
            override val appArgs: AppArguments = AppArguments.Empty
            override val isSingleTop: Boolean = true
        }
    }
}