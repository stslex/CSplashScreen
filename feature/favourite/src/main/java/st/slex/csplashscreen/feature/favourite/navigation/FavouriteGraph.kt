package st.slex.csplashscreen.feature.favourite.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.favourite.di.FavouriteComponentBuilder
import st.slex.csplashscreen.feature.favourite.ui.FavouriteScreen
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteViewModel

fun NavGraphBuilder.favouriteGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.FAVOURITE,
        featureBuilder = FavouriteComponentBuilder
    ) { viewModel: FavouriteViewModel, _ ->

        LaunchedEffect(Unit) {
            viewModel.sendAction(Action.Init)
        }

        val photos = remember {
            viewModel.state.mapState { it.photos }
        }.collectAsLazyPagingItems()

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
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