package st.slex.csplashscreen.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.MainActivity
import st.slex.csplashscreen.di.module.AppModule
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}