package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.ui.navigation.NavigationComponent
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationComponent: Lazy<NavigationComponent>

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                CSplashScreenTheme {
                    Scaffold { navigationComponent.get().InitNavHost() }
                }
            }
        }
    }
}