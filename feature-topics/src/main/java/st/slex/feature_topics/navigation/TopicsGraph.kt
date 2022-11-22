package st.slex.feature_topics.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import st.slex.core_navigation.testing.AppDestination
import st.slex.feature_topics.ui.TopicsScreen

fun NavGraphBuilder.topicsGraph(
    modifier: Modifier = Modifier
) {
    composable(route = AppDestination.TOPICS.navigationRoute) {
        TopicsScreen(
            modifier = modifier,
            viewModel = koinViewModel()
        )
    }
}