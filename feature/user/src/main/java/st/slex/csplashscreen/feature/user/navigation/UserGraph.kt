package st.slex.csplashscreen.feature.user.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.core.ui.base.setupComponent
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.user.di.UserComponentBuilder
import st.slex.csplashscreen.feature.user.ui.UserScreen
import st.slex.csplashscreen.feature.user.ui.UserViewModel
import st.slex.csplashscreen.feature.user.ui.state.rememberUserPagerState
import st.slex.csplashscreen.feature.user.ui.state.rememberUserSwipeState
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action.Init
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action.OnBackButtonClick
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action.OnCollectionClick
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action.OnImageClick
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action.OnUserClick

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.USER.navigationRoute,
        arguments = AppDestination.USER.composableArguments
    ) { navBackStackEntry ->

        val arguments = AppDestination.USER.parseArguments(navBackStackEntry).let { args ->
            AppArguments.UserScreen(args.first())
        }

        val viewModel: UserViewModel = setupComponent(
            key = arguments.username,
            builder = UserComponentBuilder
        )

        LaunchedEffect(arguments) {
            viewModel.sendAction(Init(arguments))
        }

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        val state by remember {
            viewModel.state
        }.collectAsState()

        val photos = remember(arguments) {
            state.photos(arguments.username)
        }.collectAsLazyPagingItems()

        val likes = remember(arguments) {
            state.likes(arguments.username)
        }.collectAsLazyPagingItems()

        val collections = remember(arguments) {
            state.collections(arguments.username)
        }.collectAsLazyPagingItems()

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

        UserScreen(
            modifier = modifier,
            state = state,
            userPagerState = userPagerState,
            userSwipeState = userSwipeState,
            onImageClick = remember {
                { value -> viewModel.sendAction(OnImageClick(value)) }
            },
            onUserClick = remember {
                { value -> viewModel.sendAction(OnUserClick(value)) }
            },
            onCollectionClick = remember {
                { value -> viewModel.sendAction(OnCollectionClick(value)) }
            },
            popBackStack = remember {
                { viewModel.sendAction(OnBackButtonClick) }
            },
        )
    }
}