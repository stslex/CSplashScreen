package st.slex.csplashscreen

import android.app.Application
import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.component.AppComponent
import st.slex.csplashscreen.di.component.DaggerAppComponent

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
class SplashApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).create()
    }
}

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is SplashApplication -> appComponent
        else -> (applicationContext as SplashApplication).appComponent
    }