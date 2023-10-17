package st.slex.csplashscreen.di.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.ui.InitialAppViewModel

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(InitialAppViewModel::class)
    fun bindsViewModel(impl: InitialAppViewModel): ViewModel

    @Binds
    fun bindsFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}