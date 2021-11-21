package st.slex.csplashscreen.di.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.navigation.NavigationHost

@InstallIn(SingletonComponent::class)
@Module
interface NavigationModule {


    @ExperimentalCoroutinesApi
    @ExperimentalCoilApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Binds
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost
}