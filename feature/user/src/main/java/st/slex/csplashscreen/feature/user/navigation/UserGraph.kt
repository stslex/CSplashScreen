package st.slex.csplashscreen.feature.user.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.feature.user.ui.UserScreen
import st.slex.csplashscreen.feature.user.ui.UserViewModel
import st.slex.csplashscreen.feature.user.ui.state.rememberUserScreenNavigation
import st.slex.csplashscreen.feature.user.ui.state.rememberUserScreenState

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

        val viewModel: UserViewModel = koinViewModel(
            key = arguments.username
        ) { parametersOf(arguments) }

        val photos = remember {
            viewModel.photos
        }.collectAsLazyPagingItems()

        val likes = remember {
            viewModel.likes
        }.collectAsLazyPagingItems()

        val collections = remember {
            viewModel.collections
        }.collectAsLazyPagingItems()

        val navigation = rememberUserScreenNavigation(
            username = viewModel.username,
            onImageClick = viewModel::onImageClick,
            onUserClick = viewModel::onUserClick,
            onCollectionClick = viewModel::onCollectionClick,
            popBackStack = viewModel::popBackStack,
        )

        val userScreenState = rememberUserScreenState(
            photos = photos,
            likes = likes,
            collections = collections,
            navigation = navigation,
            username = arguments.username,
            userFlow = viewModel::user
        )

        UserScreen(
            modifier = modifier,
            userScreenState = userScreenState
        )
    }
}