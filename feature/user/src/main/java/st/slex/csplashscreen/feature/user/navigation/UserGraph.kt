package st.slex.csplashscreen.feature.user.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.user.di.UserComponentBuilder
import st.slex.csplashscreen.feature.user.ui.UserScreen
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore.Action.Init
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore.Action.OnBackButtonClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore.Action.OnCollectionClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore.Action.OnImageClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore.Action.OnUserClick
import st.slex.csplashscreen.feature.user.ui.presenter.UserViewModel
import st.slex.csplashscreen.feature.user.ui.state.rememberUserPagerState
import st.slex.csplashscreen.feature.user.ui.state.rememberUserSwipeState

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.USER,
        featureBuilder = UserComponentBuilder
    ) { viewModel: UserViewModel, args ->
        val arguments = args.firstOrNull()
            .orEmpty()
            .let(AppArguments::UserScreen)

        val state by remember { viewModel.state }.collectAsState()

        val photos = remember {
            viewModel.state.mapState { it.photos }
        }.collectAsLazyPagingItems()

        val likes = remember(arguments) {
            viewModel.state.mapState { it.likes }
        }.collectAsLazyPagingItems()

        val collections = remember(arguments) {
            viewModel.state.mapState { it.collections }
        }.collectAsLazyPagingItems()

        LaunchedEffect(arguments) {
            viewModel.sendAction(Init(arguments))
        }

        viewModel.event.CollectAsEvent { event ->
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