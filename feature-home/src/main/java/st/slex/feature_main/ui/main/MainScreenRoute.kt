package st.slex.feature_main.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import com.stslex.csplashscreen.core.navigation.NavigationScreen

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