package st.slex.csplashscreen.feature.search.navigation

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
import st.slex.csplashscreen.feature.search.ui.SearchPhotosScreen
import st.slex.csplashscreen.feature.search.ui.SearchViewModel

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.SEARCH_PHOTOS.navigationRoute,
        arguments = AppDestination.SEARCH_PHOTOS.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.SEARCH_PHOTOS.parseArguments(navBackStackEntry).let { args ->
            AppArguments.SearchPhotosScreen(args[0])
        }

        val viewModel: SearchViewModel = koinViewModel(
            key = arguments.checkedQuery
        ) { parametersOf(arguments) }

        val photos = remember {
            viewModel.photosSearch
        }.collectAsLazyPagingItems()

        val searchHistory = remember {
            viewModel.searchHistory
        }.collectAsLazyPagingItems()

        SearchPhotosScreen(
            modifier = modifier,
            photos = photos,
            searchHistory = searchHistory,
            querySearch = remember {
                { viewModel.querySearch }
            },
            onQuery = viewModel::setQueryPhotosSearch,
            onUserClick = viewModel::onProfileClick,
            onImageClick = viewModel::onImageClick,
            clearHistory = viewModel::clearHistory
        )
    }
}