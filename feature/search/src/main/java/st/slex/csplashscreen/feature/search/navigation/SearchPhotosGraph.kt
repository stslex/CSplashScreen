package st.slex.csplashscreen.feature.search.navigation

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
import st.slex.csplashscreen.core.ui.base.setupComponent
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.search.di.SearchPhotosComponentBuilder
import st.slex.csplashscreen.feature.search.ui.SearchPhotosScreen
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStore.Action
import st.slex.csplashscreen.feature.search.ui.presenter.SearchViewModel

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

        val viewModel: SearchViewModel = setupComponent(
            key = arguments.hashCode().toString(),
            builder = SearchPhotosComponentBuilder
        )

        LaunchedEffect(arguments) {
            viewModel.sendAction(Action.Init(arguments))
        }

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        val state by remember {
            viewModel.state
        }.collectAsState()

        val photos = remember {
            state.searchItems()
        }.collectAsLazyPagingItems()

        val searchHistory = remember {
            state.historyItems()
        }.collectAsLazyPagingItems()

        SearchPhotosScreen(
            modifier = modifier,
            photos = photos,
            searchHistory = searchHistory,
            query = state.query,
            onQueryChange = remember {
                { value -> viewModel.sendAction(Action.OnQueryInput(value)) }
            },
            onUserClick = remember {
                { value -> viewModel.sendAction(Action.OnProfileClick(value)) }
            },
            onImageClick = remember {
                { value -> viewModel.sendAction(Action.OnImageClick(value)) }
            },
            clearHistory = remember { { viewModel.sendAction(Action.ClearHistory) } },
            onSearchHistoryClick = remember {
                { value -> viewModel.sendAction(Action.OnSearchHistoryClick(value)) }
            }
        )
    }
}