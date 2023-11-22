package st.slex.csplashscreen.feature.search.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.search.di.SearchPhotosComponentBuilder
import st.slex.csplashscreen.feature.search.ui.SearchPhotosScreen
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStore.Action
import st.slex.csplashscreen.feature.search.ui.presenter.SearchViewModel

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.SEARCH_PHOTOS,
        featureBuilder = SearchPhotosComponentBuilder
    ) { viewModel: SearchViewModel, args ->
        val arguments = args.firstOrNull().orEmpty().let(AppArguments::SearchPhotosScreen)

        val state by remember { viewModel.state }.collectAsState()
        val photos = remember(state.searchItems).collectAsLazyPagingItems()
        val searchHistory = remember(state.historyItems).collectAsLazyPagingItems()

        LaunchedEffect(arguments) {
            viewModel.sendAction(Action.Init(arguments))
        }

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

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