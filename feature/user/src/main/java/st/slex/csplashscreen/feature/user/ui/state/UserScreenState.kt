package st.slex.csplashscreen.feature.user.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.network.model.ui.user.UserModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel

@Stable
data class UserScreenState(
    val navigation: UserScreenNavigation,
    val user: UserModel?,
    val username: String,
    val userPagerState: UserPagerState,
    val userSwipeState: UserSwipeState,
)

@Composable
fun rememberUserScreenState(
    photos: LazyPagingItems<PhotoModel>,
    likes: LazyPagingItems<PhotoModel>,
    collections: LazyPagingItems<CollectionModel>,
    navigation: UserScreenNavigation,
    user: UserModel?,
    username: String,
): UserScreenState {

    val userPagerState = rememberUserPagerState(
        photos = photos,
        likes = likes,
        collections = collections,
    )

    val userSwipeState = rememberUserSwipeState(
        isOnPreFlingAllow = {
            userPagerState.isOnPreFlingAllow
        },
    )

    return remember(
        user,
        userSwipeState,
        userPagerState
    ) {
        UserScreenState(
            navigation = navigation,
            user = user,
            username = username,
            userPagerState = userPagerState,
            userSwipeState = userSwipeState,
        )
    }
}
