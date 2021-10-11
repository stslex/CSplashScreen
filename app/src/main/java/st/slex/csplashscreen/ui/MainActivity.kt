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

        val selectedItem = remember { mutableStateOf("home") }

        BottomNavigation {
            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Star, "Titles")
                },
                label = { Text(text = "titles") },
                selected = selectedItem.value == "titles",
                onClick = {
                    selectedItem.value = "titles"
                    navController.navigate(NavDest.TitlesScreen.destination)
                },
                alwaysShowLabel = false
            )
            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Home, "Home")
                },
                label = { Text(text = "home") },
                selected = selectedItem.value == "home",
                onClick = {
                    selectedItem.value = "home"
                    navController.navigate(NavDest.MainScreen.destination)
                },
                alwaysShowLabel = false
            )
            BottomNavigationItem(
                icon = {
                    Icon(Icons.Filled.Search, "")
                },
                label = { Text(text = "search") },
                selected = selectedItem.value == "search",
                onClick = {
                    selectedItem.value = "search"
                    navController.navigate("${NavDest.SearchPhotosScreen.destination}/ ")
                },
                alwaysShowLabel = false
            )
        }
    }
}