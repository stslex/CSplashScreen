package st.slex.csplashscreen.di.app

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import st.slex.csplashscreen.SplashApplication
import st.slex.csplashscreen.core.core.api.AppApi
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: SplashApplication)
}
