package st.slex.csplashscreen.feature.favourite.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.core.navigation.NavExt.composableArguments
import st.slex.csplashscreen.feature.favourite.ui.FavouriteScreen
import st.slex.csplashscreen.feature.favourite.ui.FavouriteViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.favouriteGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.FAVOURITE.navigationRoute,
        arguments = AppDestination.FAVOURITE.composableArguments
    ) {

        val viewModel: FavouriteViewModel = koinViewModel()

        val photos = remember {
            viewModel.photos
        }.collectAsLazyPagingItems()

        FavouriteScreen(
            modifier = modifier,
            photos = photos,
            onUserClick = viewModel::onUserClick,
            onImageClick = viewModel::onImageClick,
            onGoToPhotosClick = viewModel::onGoToPhotosClick
        )
    }
}