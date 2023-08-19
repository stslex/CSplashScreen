package st.slex.csplashscreen.feature.collection.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.core.navigation.NavExt.parseArguments
import st.slex.csplashscreen.feature.collection.ui.CollectionScreen
import st.slex.csplashscreen.feature.collection.ui.SingleCollectionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
        val viewModel: SingleCollectionViewModel = koinViewModel(
            key = arguments.collectionId
        ) {
            parametersOf(arguments)
        }

        val photos = remember {
            viewModel.photos
        }.collectAsLazyPagingItems()

        CollectionScreen(
            modifier = modifier,
            photos = photos,
            onImageClick = viewModel::onImageClick,
            onProfileClick = viewModel::onProfileClick
        )
    }
}