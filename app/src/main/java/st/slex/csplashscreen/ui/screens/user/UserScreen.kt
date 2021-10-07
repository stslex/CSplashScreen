package st.slex.csplashscreen.ui.screens.user

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
interface UserScreen {

    @Composable
    fun BindScreen(
        args: NavBackStackEntry,
        viewModel: UserViewModel
    )

    class Base @Inject constructor() : UserScreen {

        @Inject
        lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

        @Composable
        override fun BindScreen(
            args: NavBackStackEntry,
            viewModel: UserViewModel
        ) {
            val username = args.arguments?.getString("username").toString()
        }

    }
}