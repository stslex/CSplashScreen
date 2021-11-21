package st.slex.csplashscreen.di.component

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.module.AppModule
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen
import javax.inject.Singleton

@Singleton
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(screen: MainScreen)
    fun inject(screen: TopicsScreen)
    fun inject(screen: SingleCollectionScreen)
    fun inject(screen: ImageDetailScreen)
    fun inject(screen: SearchPhotosScreen)
    fun inject(screen: UserScreen)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}