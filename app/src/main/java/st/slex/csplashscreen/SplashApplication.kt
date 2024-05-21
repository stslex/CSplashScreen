package st.slex.csplashscreen

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import st.slex.csplashscreen.di.AppModules

class SplashApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(
                level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE
            )
            androidContext(this@SplashApplication)
            modules(AppModules)
        }
    }
}