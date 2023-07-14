package com.stslex.csplashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.feature.collection.navigation.singleCollectionGraph
import st.slex.feature_image_raw.navigation.rawImageGraph
import com.stslex.csplashscreen.feature.home.navigation.homeGraph
import st.slex.feature_photo_detail.navigation.imageDetailGraph
import com.stslex.csplashscreen.feature.search.navigation.searchPhotosGraph
import com.stslex.csplashscreen.feature.topics.navigation.topicsGraph
import com.stslex.csplashscreen.feature.user.navigation.userGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.HOME
) {
    val navigator: (NavigationScreen) -> Unit = { screen ->
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navController.navigateScreen(screen)
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination.route
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
