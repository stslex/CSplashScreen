package st.slex.csplashscreen.feature.user.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
data class UserScreenNavigation(
    val popBackStack: () -> Unit,
    val onUserClick: (username: String) -> Unit,
    val onImageClick: (id: String) -> Unit,
    val onCollectionClick: (id: String) -> Unit,
)

@Composable
fun rememberUserScreenNavigation(
    username: String,
    popBackStack: () -> Unit,
    onUserClick: (username: String) -> Unit,
    onImageClick: (id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
): UserScreenNavigation = remember(username) {
    UserScreenNavigation(
        onImageClick = onImageClick,
        onUserClick = onUserClick,
        onCollectionClick = onCollectionClick,
        popBackStack = popBackStack,
    )
}
