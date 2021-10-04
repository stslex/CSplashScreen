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
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ExperimentalCoilApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}