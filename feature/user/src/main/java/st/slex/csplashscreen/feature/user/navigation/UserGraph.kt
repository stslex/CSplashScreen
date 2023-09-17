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
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.user.di.setupUserComponent
import st.slex.csplashscreen.feature.user.ui.UserScreen
import st.slex.csplashscreen.feature.user.ui.state.rememberUserScreenNavigation
import st.slex.csplashscreen.feature.user.ui.state.rememberUserScreenState
import st.slex.csplashscreen.feature.user.ui.store.UserStore
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

        val viewModel = setupUserComponent(arguments.username)

        LaunchedEffect(arguments) {
            viewModel.sendAction(Init(arguments))
        }

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is UserStore.Event.Navigation -> viewModel.processNavigation(event)
            }
        }

        val state by remember {
            viewModel.state
        }.collectAsState()

        val photos = remember {
            state.photos()
        }.collectAsLazyPagingItems()

        val likes = remember {
            state.likes()
        }.collectAsLazyPagingItems()

        val collections = remember {
            state.collections()
        }.collectAsLazyPagingItems()

        val navigation = rememberUserScreenNavigation(
            username = arguments.username,
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

        val userScreenState = rememberUserScreenState(
            user = state.user,
            photos = photos,
            likes = likes,
            collections = collections,
            navigation = navigation,
            username = arguments.username,
        )

        UserScreen(
            modifier = modifier,
            userScreenState = userScreenState
        )
    }
}