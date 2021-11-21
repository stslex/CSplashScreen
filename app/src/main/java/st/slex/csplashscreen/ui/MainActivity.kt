package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.ui.navigation.NavHostResource
import st.slex.csplashscreen.ui.navigation.NavigationHost
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@FlowPreview
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHost: Lazy<NavigationHost>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ProvideWindowInsets {
                CSplashScreenTheme {
                    Scaffold(
                        bottomBar = { MainBottomAppBar(navController = navController) }
                    ) {
                        navigationHost.get().CreateNavigationHost(navController = navController)
                    }
                }
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    private fun MainBottomAppBar(navController: NavController) {
        val listOfItems: List<BottomAppBarResource> = listOf(
            BottomAppBarResource.TopicsScreen,
            BottomAppBarResource.MainScreen,
            BottomAppBarResource.SearchScreen
        )
        BottomAppBar(modifier = Modifier.fillMaxWidth()) {
            val selectedItem =
                remember { mutableStateOf(BottomAppBarResource.MainScreen.destination) }
            BottomNavigation {
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
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        inclusive = true
                                        saveState = true
                                    }
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

    private sealed interface BottomAppBarResource {

        val destination: String
        val icon: ImageVector
        val route: String
            get() = destination

        object MainScreen : BottomAppBarResource {
            override val destination: String = NavHostResource.MainScreen.destination
            override val icon: ImageVector = Icons.Filled.Home
        }

        object TopicsScreen : BottomAppBarResource {
            override val destination: String = NavHostResource.TopicsScreen.destination
            override val icon: ImageVector = Icons.Filled.Star
        }

        object SearchScreen : BottomAppBarResource {
            override val destination: String = NavHostResource.SearchPhotosScreen.destination
            override val icon: ImageVector = Icons.Filled.Search
            override val route: String = "$destination/ "
        }
    }
}

