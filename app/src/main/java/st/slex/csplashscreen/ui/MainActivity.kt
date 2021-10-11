package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.ui.navigation.NavDest
import st.slex.csplashscreen.ui.navigation.NavHost
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            CSplashScreenTheme {
                val navController = rememberNavController()
                ProvideWindowInsets {
                    Scaffold(
                        bottomBar = { MainBottomAppBar(navController = navController) }
                    ) { NavHost(navController = navController) }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun MainBottomAppBar(navController: NavController) {
    BottomAppBar(modifier = Modifier.fillMaxWidth()) {

        val listOfItems = listOf(
            MainBottomAppbarState.TopicsScreen,
            MainBottomAppbarState.MainScreen,
            MainBottomAppbarState.SearchScreen
        )
        val selectedItem = remember { mutableStateOf(NavDest.MainScreen.destination) }

        BottomNavigation {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentDestination = navBackStackEntry?.destination

            listOfItems.forEach {
                BottomNavigationItem(
                    icon = {
                        Icon(it.icon, it.destination)
                    },
                    label = { Text(text = it.destination) },
                    selected = selectedItem.value == it.destination,
                    onClick = {
                        selectedItem.value = it.destination
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

private sealed interface MainBottomAppbarState {
    val destination: String
    val icon: ImageVector
    val route: String

    object MainScreen : MainBottomAppbarState {
        override val destination: String = NavDest.MainScreen.destination
        override val icon: ImageVector = Icons.Filled.Home
        override val route: String = destination
    }

    object TopicsScreen : MainBottomAppbarState {
        override val destination: String = NavDest.TopicsScreen.destination
        override val icon: ImageVector = Icons.Filled.Star
        override val route: String = destination
    }

    object SearchScreen : MainBottomAppbarState {
        override val destination: String = NavDest.SearchPhotosScreen.destination
        override val icon: ImageVector = Icons.Filled.Search
        override val route: String = "$destination/ "
    }
}