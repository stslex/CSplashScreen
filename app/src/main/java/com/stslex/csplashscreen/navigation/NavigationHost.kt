package com.stslex.csplashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.feature.collection.navigation.singleCollectionGraph
import com.stslex.csplashscreen.feature.favourite.navigation.favouriteGraph
import com.stslex.csplashscreen.feature.feature_photo_detail.navigation.imageDetailGraph
import com.stslex.csplashscreen.feature.home.navigation.homeGraph
import com.stslex.csplashscreen.feature.search.navigation.searchPhotosGraph
import com.stslex.csplashscreen.feature.user.navigation.userGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        homeGraph(modifier)
        userGraph(modifier)
        imageDetailGraph(modifier)
        searchPhotosGraph(modifier)
        singleCollectionGraph(modifier)
        favouriteGraph(modifier)
    }
}
