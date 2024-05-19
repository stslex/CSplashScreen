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
import st.slex.csplashscreen.feature.collection.di.SingleCollectionBuilder
import st.slex.csplashscreen.feature.collection.ui.CollectionScreen
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionViewModel

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
) {
    createScreen(
        appDestination = AppDestination.COLLECTION,
        featureBuilder = SingleCollectionBuilder
    ) { viewModel: SingleCollectionViewModel, args ->
        val arguments = args.first().let(AppArguments::CollectionScreen)
        val photos = remember { viewModel.state.mapState { it.photos } }.collectAsLazyPagingItems()

        LaunchedEffect(Unit) {
            viewModel.sendAction(Action.Init(arguments.collectionId))
        }

        viewModel.event.CollectAsEvent { event ->
            // TODO NOT IMPLEMENTED YET
        }

        CollectionScreen(
            modifier = modifier,
            photos = photos,
            onImageClick = remember {
                { uuid ->
                    viewModel.sendAction(Action.OnImageClick(uuid))
                }
            },
            onProfileClick = remember {
                { username ->
                    viewModel.sendAction(Action.OnProfileClick(username))
                }
            }
        )
    }
}