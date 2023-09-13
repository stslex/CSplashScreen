package st.slex.csplashscreen.feature.collection.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.core.ui.utils.CollectAsEvent
import st.slex.csplashscreen.feature.collection.di.setupComponent
import st.slex.csplashscreen.feature.collection.ui.CollectionScreen
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.COLLECTION.navigationRoute,
        arguments = AppDestination.COLLECTION.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.COLLECTION.parseArguments(navBackStackEntry).let { args ->
            AppArguments.CollectionScreen(args[0])
        }
        val viewModel = setupComponent(key = arguments.collectionId)

        val state by remember {
            viewModel.state
        }.collectAsState()

        val photos = remember {
            state.photos()
        }.collectAsLazyPagingItems()

        LaunchedEffect(Unit) {
            viewModel.sendAction(Action.Init(arguments.collectionId))
        }

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is Event.Navigation -> viewModel.processNavigation(event)
            }
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