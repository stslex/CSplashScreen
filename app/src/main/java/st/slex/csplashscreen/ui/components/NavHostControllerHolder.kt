package st.slex.csplashscreen.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.ui.components.bottom_appbar.BottomAppBarResource

@Stable
class NavHostControllerHolder private constructor(
    val navController: NavHostController,
    val bottomBarDestination: State<Screen?>
) {

    companion object {

        @Composable
        fun rememberNavHostControllerHolder(): NavHostControllerHolder {
            val controller = rememberNavController()
            val bottomBarDestination = remember {
                mutableStateOf<Screen?>(Screen.Home)
            }
            DisposableEffect(controller) {
                val listener = OnDestinationChangedListener { _, destination, _ ->
                    bottomBarDestination.value =
                        destination.route?.let(BottomAppBarResource::getByRoute)
                }
                controller.addOnDestinationChangedListener(listener)
                onDispose {
                    controller.removeOnDestinationChangedListener(listener)
                }
            }
            return remember(controller) {
                NavHostControllerHolder(
                    navController = controller,
                    bottomBarDestination = bottomBarDestination
                )
            }
        }
    }
}