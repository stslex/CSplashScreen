package st.slex.csplashscreen.feature.user.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.ui.base.screen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.user.ui.UserScreen
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Action.Init
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Action.OnBackButtonClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Action.OnCollectionClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Action.OnImageClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Action.OnUserClick
import st.slex.csplashscreen.feature.user.ui.state.rememberUserPagerState
import st.slex.csplashscreen.feature.user.ui.state.rememberUserSwipeState

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier,
) {
    screen<Screen.UserScreen, UserStore> { screen, store ->

        val state by remember { store.state }.collectAsState()

        val photos = remember {
            store.state.mapState { it.photos }
        }.collectAsLazyPagingItems()

        val likes = remember(screen) {
            store.state.mapState { it.likes }
        }.collectAsLazyPagingItems()

        val collections = remember(screen) {
            store.state.mapState { it.collections }
        }.collectAsLazyPagingItems()

        LaunchedEffect(screen) {
            store.sendAction(Init(screen))
        }

        store.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        val userPagerState = rememberUserPagerState(
            photos = photos,
            likes = likes,
            collections = collections,
        )

        val userSwipeState = rememberUserSwipeState(
            isOnPreFlingAllow = { userPagerState.isOnPreFlingAllow },
        )

        UserScreen(
            modifier = modifier,
            state = state,
            userPagerState = userPagerState,
            userSwipeState = userSwipeState,
            onImageClick = remember {
                { value -> store.sendAction(OnImageClick(value)) }
            },
            onUserClick = remember {
                { value -> store.sendAction(OnUserClick(value)) }
            },
            onCollectionClick = remember {
                { value -> store.sendAction(OnCollectionClick(value)) }
            },
            popBackStack = remember {
                { store.sendAction(OnBackButtonClick) }
            },
        )
    }
}