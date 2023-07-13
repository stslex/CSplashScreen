package com.stslex.csplashscreen.feature.user.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavExt.composableArguments
import com.stslex.csplashscreen.core.navigation.NavExt.parseArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.stslex.csplashscreen.feature.user.ui.UserScreen
import com.stslex.csplashscreen.feature.user.ui.UserViewModel

fun NavGraphBuilder.userGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
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
        ) { parametersOf(arguments, navigate) }

        val photos = remember {
            viewModel.photos
        }.collectAsLazyPagingItems()

        val likes = remember {
            viewModel.likes
        }.collectAsLazyPagingItems()

        val collections = remember {
            viewModel.collections
        }.collectAsLazyPagingItems()

        UserScreen(
            modifier = modifier,
            photos = photos,
            likes = likes,
            collections = collections,
            onImageClick = viewModel::onImageClick,
            onUserClick = viewModel::onUserClick,
            onCollectionClick = viewModel::onCollectionClick,
            popBackStack = viewModel::popBackStack,
            username = arguments.username,
            userFlow = viewModel::user
        )
    }
}