package st.slex.csplashscreen.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.core.ViewModelFactory

@ExperimentalCoroutinesApi
@Module(includes = [ViewModelModule::class])
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}