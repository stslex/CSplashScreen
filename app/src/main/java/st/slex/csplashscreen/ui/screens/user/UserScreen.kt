package st.slex.csplashscreen.ui.screens.user

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
fun UserScreen(
    args: NavBackStackEntry,
    navController: NavController,
    viewModel: UserViewModel
) {
    val username = args.arguments?.getString("username").toString()
}