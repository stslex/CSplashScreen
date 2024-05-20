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
import st.slex.csplashscreen.feature.favourite.ui.FavouriteScreen
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStoreComponent.Action

fun NavGraphBuilder.favouriteGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.FAVOURITE,
    ) { store: FavouriteStore, _ ->

        LaunchedEffect(Unit) {
            store.sendAction(Action.Init)
        }

        val photos = remember {
            store.state.mapState { it.photos }
        }.collectAsLazyPagingItems()

        store.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        FavouriteScreen(
            modifier = modifier,
            photos = photos,
            onUserClick = remember {
                { username -> store.sendAction(Action.OnUserClick(username)) }
            },
            onImageClick = remember {
                { uuid -> store.sendAction(Action.OnImageClick(uuid)) }
            },
            onGoToPhotosClick = remember {
                { store.sendAction(Action.GoToPhotosClick) }
            }
        )
    }
}