package st.slex.csplashscreen.feature.collection.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.core.coroutine.CoroutineExt.mapState
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.ui.base.createScreen
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.collection.ui.CollectionScreen
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStore
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStoreComponent.Action

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(AppDestination.COLLECTION) { store: SingleCollectionStore, args ->
        val arguments = args.first().let(AppArguments::CollectionScreen)
        val photos = remember { store.state.mapState { it.photos } }.collectAsLazyPagingItems()

        LaunchedEffect(Unit) {
            store.sendAction(Action.Init(arguments.collectionId))
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