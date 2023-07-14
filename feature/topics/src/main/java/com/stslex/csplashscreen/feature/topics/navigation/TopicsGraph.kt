package com.stslex.csplashscreen.feature.topics.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.feature.topics.ui.TopicsScreen
import com.stslex.csplashscreen.feature.topics.ui.TopicsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.topicsGraph(
    modifier: Modifier = Modifier,
    navigate: (NavigationScreen) -> Unit
) {
    composable(route = AppDestination.TOPICS.navigationRoute) {

        val viewModel: TopicsViewModel = koinViewModel(
            parameters = { parametersOf(navigate) }
        )

        val topics = remember {
            viewModel.topics
        }.collectAsLazyPagingItems()

        TopicsScreen(
            modifier = modifier,
            topics = topics
        )
    }
}