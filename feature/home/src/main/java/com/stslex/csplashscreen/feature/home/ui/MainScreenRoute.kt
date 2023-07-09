package com.stslex.csplashscreen.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    navigator: (NavigationScreen) -> Unit,
) {
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
                navigator(NavigationScreen.UserScreen(username))
            }
        },
        navToCollection = remember {
            { uuid ->
                navigator(NavigationScreen.CollectionScreen(uuid))
            }
        },
        navToImage = remember {
            { url, uuid ->
                navigator(
                    NavigationScreen.ImageDetailScreen(
                        url = url,
                        imageId = uuid
                    )
                )
            }
        },
        collections = remember { collections },
        photos = remember { photos }
    )
}