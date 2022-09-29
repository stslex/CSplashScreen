package st.slex.feature_main.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenRoute(
    modifier: Modifier = Modifier,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    systemUiController: SystemUiController = rememberSystemUiController(),
    viewModel: MainScreenViewModel = hiltViewModel(),
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
        onProfileClick = onProfileClick,
        onImageClick = onImageClick,
        onCollectionClick = onCollectionClick,
        viewModel = viewModel
    )
}