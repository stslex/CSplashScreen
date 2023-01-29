package st.slex.feature_main.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import st.slex.core_navigation.NavigationScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    navigator: (NavigationScreen) -> Unit,
    viewModel: MainScreenViewModel = koinViewModel(
        parameters = { parametersOf(navigator) }
    ),
) {
    MainScreen(
        modifier = modifier,
        viewModel = viewModel
    )
}