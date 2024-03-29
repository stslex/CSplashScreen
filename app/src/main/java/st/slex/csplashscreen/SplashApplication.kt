package st.slex.csplashscreen

import android.app.Application
import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.core.api.ApplicationApiProvider
import st.slex.csplashscreen.di.app.AppComponent
import st.slex.csplashscreen.di.app.DaggerAppComponent

class SplashApplication : Application(), ApplicationApiProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override val appApi: AppApi
        get() = appComponent

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }
}