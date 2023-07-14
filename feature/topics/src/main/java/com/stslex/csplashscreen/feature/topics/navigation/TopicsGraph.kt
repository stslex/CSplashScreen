package com.stslex.csplashscreen.feature.topics.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.feature.topics.ui.TopicsScreen

fun NavGraphBuilder.topicsGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(route = AppDestination.TOPICS.navigationRoute) {
        TopicsScreen(
            modifier = modifier,
            viewModel = koinViewModel(parameters = { parametersOf(navigate) })
        )
    }
}