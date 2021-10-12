package st.slex.csplashscreen.ui.screens.user

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun UserScreen(
    navController: NavController,
    username: String,
    viewModel: MainScreenViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {

}