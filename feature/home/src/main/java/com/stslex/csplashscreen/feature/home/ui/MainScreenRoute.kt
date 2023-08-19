package com.stslex.csplashscreen.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreenRoute(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<MainScreenViewModel>()

    val collections = remember {
        viewModel.collections
    }.collectAsLazyPagingItems()

    val photos = remember {
        viewModel.photos
    }.collectAsLazyPagingItems()

    MainScreen(
        modifier = modifier,
        navToProfile = remember {
            { username ->
                viewModel.navigate(NavigationScreen.UserScreen(username))
            }
        },
        navToCollection = remember {
            { uuid ->
                viewModel.navigate(NavigationScreen.CollectionScreen(uuid))
            }
        },
        navToImage = remember {
            { uuid ->
                viewModel.navigate(NavigationScreen.ImageDetailScreen(uuid))
            }
        },
        collections = remember { collections },
        photos = remember { photos }
    )
}