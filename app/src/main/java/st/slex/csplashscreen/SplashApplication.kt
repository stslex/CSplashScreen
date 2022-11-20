package st.slex.csplashscreen

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import st.slex.csplashscreen.di.module.AllModules

class SplashApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@SplashApplication)
            modules(AllModules().allModules)
        }
        super.onCreate()
    }
}