package com.stslex.csplashscreen.feature.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.feature.home.ui.MainScreenRoute

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        MainScreenRoute(modifier)
    }
}
