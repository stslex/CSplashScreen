package st.slex.csplashscreen.di.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.navigation.NavigationHost
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Singleton

@Module
interface NavigationModule {

    @ExperimentalCoroutinesApi
    @ExperimentalCoilApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    //@Singleton
    @ExperimentalAnimationApi
    @Binds
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost

    @Singleton
    @Binds
    fun bindsNavigator(navigator: Navigator.Base): Navigator
}