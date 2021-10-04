package st.slex.csplashscreen.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.key.ViewModelKey
import st.slex.csplashscreen.ui.main.MainViewModel

@ExperimentalCoroutinesApi
@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindsPhotosViewModel(viewModel: MainViewModel): ViewModel
}