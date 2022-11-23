package st.slex.feature_main.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
    }
    MainScreen(
        modifier = modifier,
        viewModel = viewModel
    )
}