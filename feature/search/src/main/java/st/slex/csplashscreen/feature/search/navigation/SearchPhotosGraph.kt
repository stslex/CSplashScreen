package st.slex.csplashscreen.feature.search.navigation

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
import st.slex.csplashscreen.feature.search.ui.SearchPhotosScreen
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStore
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.Action

fun NavGraphBuilder.searchPhotosGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(AppDestination.SEARCH_PHOTOS) { store: SearchStore, args ->
        val arguments = args.firstOrNull().orEmpty().let(AppArguments::SearchPhotosScreen)

        val state by remember { store.state }.collectAsState()
        val photos = remember {
            store.state.mapState { it.searchItems }
        }.collectAsLazyPagingItems()
        val searchHistory = remember {
            store.state.mapState { it.historyItems }
        }.collectAsLazyPagingItems()

        LaunchedEffect(arguments) {
            store.sendAction(Action.Init(arguments))
        }

        store.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        SearchPhotosScreen(
            modifier = modifier,
            photos = photos,
            searchHistory = searchHistory,
            query = state.query,
            onQueryChange = remember {
                { value -> store.sendAction(Action.OnQueryInput(value)) }
            },
            onUserClick = remember {
                { value -> store.sendAction(Action.OnProfileClick(value)) }
            },
            onImageClick = remember {
                { value -> store.sendAction(Action.OnImageClick(value)) }
            },
            clearHistory = remember { { store.sendAction(Action.ClearHistory) } },
            onSearchHistoryClick = remember {
                { value -> store.sendAction(Action.OnSearchHistoryClick(value)) }
            }
        )
    }
}