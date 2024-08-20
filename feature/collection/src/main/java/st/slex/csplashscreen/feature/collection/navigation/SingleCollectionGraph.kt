package st.slex.csplashscreen.feature.collection.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.ui.base.screen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.collection.ui.CollectionScreen
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStore
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStoreComponent.Action

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
) {
    screen { screen: Screen.CollectionScreen, store: SingleCollectionStore ->
        val photos = remember { store.state.mapState { it.photos } }.collectAsLazyPagingItems()

        LaunchedEffect(Unit) {
            store.sendAction(Action.Init(screen.collectionId))
        }

        store.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        CollectionScreen(
            modifier = modifier,
            photos = photos,
            onImageClick = remember {
                { uuid ->
                    store.sendAction(Action.OnImageClick(uuid))
                }
            },
            onProfileClick = remember {
                { username ->
                    store.sendAction(Action.OnProfileClick(username))
                }
            }
        )
    }
}