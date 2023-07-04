package com.stslex.csplashscreen.feature.collection.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavExt.composableArguments
import com.stslex.csplashscreen.core.navigation.NavExt.parseArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.feature.collection.ui.CollectionScreen

fun NavGraphBuilder.singleCollectionGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(
        route = AppDestination.COLLECTION.navigationRoute,
        arguments = AppDestination.COLLECTION.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.COLLECTION.parseArguments(navBackStackEntry).let { args ->
            AppArguments.CollectionScreen(args[0])
        }
        CollectionScreen(
            modifier = modifier,
            viewModel = koinViewModel { parametersOf(arguments, navigate) }
        )
    }
}