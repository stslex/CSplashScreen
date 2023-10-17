package st.slex.csplashscreen.feature.favourite.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.favourite.di.setupComponent
import st.slex.csplashscreen.feature.favourite.ui.FavouriteScreen
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event

fun NavGraphBuilder.favouriteGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.FAVOURITE.navigationRoute,
        arguments = AppDestination.FAVOURITE.composableArguments
    ) {

        val viewModel = setupComponent()

        val state by remember {
            viewModel.state
        }.collectAsState()

        val photos = remember {
            state.photos()
        }.collectAsLazyPagingItems()

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is Event.Navigation -> viewModel.navigate(event)
            }
        }

        FavouriteScreen(
            modifier = modifier,
            photos = photos,
            onUserClick = remember {
                { username -> viewModel.sendAction(Action.OnUserClick(username)) }
            },
            onImageClick = remember {
                { uuid -> viewModel.sendAction(Action.OnImageClick(uuid)) }
            },
            onGoToPhotosClick = remember {
                { viewModel.sendAction(Action.GoToPhotosClick) }
            }
        )
    }
}