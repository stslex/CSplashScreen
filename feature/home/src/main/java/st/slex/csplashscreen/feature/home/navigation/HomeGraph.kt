package st.slex.csplashscreen.feature.home.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.home.di.HomeComponentBuilder
import st.slex.csplashscreen.feature.home.ui.MainScreen
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore
import st.slex.csplashscreen.feature.home.ui.presenter.HomeViewModel

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.HOME,
        featureBuilder = HomeComponentBuilder
    ) { viewModel: HomeViewModel, _ ->

        val state by remember { viewModel.state }.collectAsState()

        val collections = remember(state.collections).collectAsLazyPagingItems()
        val photos = remember(state.photos).collectAsLazyPagingItems()

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        MainScreen(
            modifier = modifier,
            navToProfile = remember {
                { username ->
                    viewModel.sendAction(HomeStore.Action.OnUserClick(username))
                }
            },
            navToCollection = remember {
                { uuid ->
                    viewModel.sendAction(HomeStore.Action.OnCollectionClick(uuid))
                }
            },
            navToImage = remember {
                { uuid ->
                    viewModel.sendAction(HomeStore.Action.OnImageClick(uuid))
                }
            },
            collections = remember { collections },
            photos = remember { photos }
        )
    }
}
