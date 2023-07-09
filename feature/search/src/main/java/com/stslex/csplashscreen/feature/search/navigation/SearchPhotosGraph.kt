package com.stslex.csplashscreen.feature.search.navigation

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
import com.stslex.csplashscreen.feature.search.ui.SearchPhotosScreen
import com.stslex.csplashscreen.feature.search.ui.SearchViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.SEARCH_PHOTOS.navigationRoute,
        arguments = AppDestination.SEARCH_PHOTOS.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.SEARCH_PHOTOS.parseArguments(navBackStackEntry).let { args ->
            AppArguments.SearchPhotosScreen(args[0])
        }

        val viewModel: SearchViewModel = koinViewModel { parametersOf(arguments, navigate) }

        val photos = remember {
            viewModel.photosSearch
        }.collectAsLazyPagingItems()

        SearchPhotosScreen(
            modifier = modifier,
            photos = photos,
            querySearch = remember {
                { viewModel.querySearch }
            },
            onQuery = viewModel::setQueryPhotosSearch,
            onUserClick = viewModel::onProfileClick,
            onImageClick = viewModel::onImageClick,
        )
    }
}