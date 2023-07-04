package com.stslex.csplashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import st.slex.core_navigation.AppDestination
import st.slex.core_navigation.NavigationScreen
import st.slex.feature_collection.navigation.singleCollectionGraph
import st.slex.feature_image_raw.navigation.rawImageGraph
import st.slex.feature_main.navigation.homeGraph
import st.slex.feature_photo_detail.navigation.imageDetailGraph
import st.slex.feature_search_photos.navigation.searchPhotosGraph
import st.slex.feature_topics.navigation.topicsGraph
import st.slex.feature_user.navigation.userGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppDestination.HOME.route
) {
    val navigator: (NavigationScreen) -> Unit = { screen ->
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navController.navigateScreen(screen)
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeGraph(modifier = modifier, navigate = navigator)
        userGraph(modifier = modifier, navigate = navigator)
        imageDetailGraph(modifier = modifier, navigate = navigator)
        rawImageGraph(modifier = modifier, navigate = navigator)
        searchPhotosGraph(modifier = modifier, navigate = navigator)
        topicsGraph(modifier = modifier, navigate = navigator)
        singleCollectionGraph(modifier = modifier, navigate = navigator)
    }
}

fun NavHostController.navigateScreen(screen: NavigationScreen) {
    navigate(screen.screenRoute) {
        if (screen.isSingleTop.not()) return@navigate
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = false
    }
}
