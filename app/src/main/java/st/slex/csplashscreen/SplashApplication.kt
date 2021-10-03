package st.slex.csplashscreen

import android.app.Application
import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.component.AppComponent
import st.slex.csplashscreen.di.component.DaggerAppComponent

@ExperimentalCoroutinesApi
class SplashApplication : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .application(this)
            .create()
    }
}

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is SplashApplication -> appComponent
        else -> (applicationContext as SplashApplication).appComponent
    }