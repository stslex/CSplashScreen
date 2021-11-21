package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@FlowPreview
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
            ProvideWindowInsets {
                CSplashScreenTheme {
                    TabNavigator(tab = HomeTab) {
                        Scaffold(
                            content = { CurrentScreen() },
                            bottomBar = {
                                BottomNavigation {
                                    TabNavigationItem(tab = TopicsTab)
                                    TabNavigationItem(tab = HomeTab)
                                    TabNavigationItem(tab = SearchTab)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }

    object HomeTab : Tab {

        override val options: TabOptions
            @Composable
            get() {
                val title = "home"
                val icon = rememberVectorPainter(image = Icons.Filled.Home)

                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon
                    )
                }
            }

        @Composable
        override fun Content() {
            Navigator(screen = MainScreen())
        }
    }

    object TopicsTab : Tab {

        override val options: TabOptions
            @Composable
            get() {
                val title = "topics"
                val icon = rememberVectorPainter(image = Icons.Filled.Star)

                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }

        @Composable
        override fun Content() {
            Navigator(screen = TopicsScreen())
        }
    }

    object SearchTab : Tab {

        override val options: TabOptions
            @Composable
            get() {
                val title = "search"
                val icon = rememberVectorPainter(image = Icons.Filled.Search)

                return remember {
                    TabOptions(
                        index = 2u,
                        title = title,
                        icon = icon
                    )
                }
            }

        @Composable
        override fun Content() {
            Navigator(screen = SearchPhotosScreen(" "))
        }
    }
}