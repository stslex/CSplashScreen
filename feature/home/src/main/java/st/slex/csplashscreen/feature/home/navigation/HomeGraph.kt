package st.slex.csplashscreen.feature.home.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.ui.base.screen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.home.ui.MainScreen
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.Action

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    screen<Screen.Home, HomeStore> { _, store ->

        LaunchedEffect(Unit) {
            store.sendAction(Action.Init)
        }

        val collections = remember {
            store.state.mapState { it.collections }
        }.collectAsLazyPagingItems()

        val photos = remember {
            store.state.mapState { it.photos }
        }.collectAsLazyPagingItems()

        store.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        MainScreen(
            modifier = modifier,
            navToProfile = remember {
                { username ->
                    store.sendAction(Action.OnUserClick(username))
                }
            },
            navToCollection = remember {
                { uuid ->
                    store.sendAction(Action.OnCollectionClick(uuid))
                }
            },
            navToImage = remember {
                { uuid ->
                    store.sendAction(Action.OnImageClick(uuid))
                }
            },
            collections = remember { collections },
            photos = remember { photos }
        )
    }
}
