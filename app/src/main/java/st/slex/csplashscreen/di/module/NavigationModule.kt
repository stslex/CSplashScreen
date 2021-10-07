package st.slex.csplashscreen.di.module

import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.navigation.NavigationActions
import st.slex.csplashscreen.ui.navigation.NavigationComponent
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Module
interface NavigationModule {

    @Singleton
    @Binds
    fun bindsNavigationComponent(navigation: NavigationComponent.Base): NavigationComponent

    @Singleton
    @Binds
    fun bindsNavigator(navigator: Navigator.Base): Navigator

    @Singleton
    @Binds
    fun bindsNavigationActions(actions: NavigationActions.Base): NavigationActions
}