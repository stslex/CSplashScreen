package st.slex.feature_topics.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.AppDestination
import st.slex.core_navigation.NavigationScreen
import st.slex.feature_topics.ui.TopicsScreen

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