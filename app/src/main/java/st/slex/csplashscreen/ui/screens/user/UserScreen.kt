package st.slex.csplashscreen.ui.screens.user

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import javax.inject.Inject


interface UserScreen {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @Composable
    fun BindScreen(args: NavBackStackEntry, navController: NavController)

    class Base @Inject constructor() : UserScreen {

        @Inject
        lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @ExperimentalPagerApi
        @Composable
        override fun BindScreen(args: NavBackStackEntry, navController: NavController) {
            val viewModel: UserViewModel = viewModel(factory = viewModelFactory.get())
            val username = args.arguments?.getString("username").toString()
        }
    }
}