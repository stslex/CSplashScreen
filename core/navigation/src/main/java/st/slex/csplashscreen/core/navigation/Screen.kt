package st.slex.csplashscreen.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
@Stable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class ImageDetailScreen(val imageId: String) : Screen

    @Serializable
    data class CollectionScreen(val collectionId: String) : Screen

    @Serializable
    data class SearchPhotosScreen(val query: String) : Screen

    @Serializable
    data class UserScreen(val username: String) : Screen

    @Serializable
    data object Favourite : Screen

}

inline fun <reified S : Screen> NavGraphBuilder.navScreen(
    noinline content: @Composable AnimatedContentScope.(S) -> Unit
) {
    composable<S> { backStackEntry ->
        content(backStackEntry.toRoute())
    }
}