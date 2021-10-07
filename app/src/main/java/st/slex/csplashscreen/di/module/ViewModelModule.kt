package st.slex.csplashscreen.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.key.ViewModelKey
import st.slex.csplashscreen.ui.detail.DetailPhotoViewModel
import st.slex.csplashscreen.ui.main.MainScreenViewModel
import st.slex.csplashscreen.ui.search_photos.SearchViewModel
import st.slex.csplashscreen.ui.user.UserViewModel

@ExperimentalCoroutinesApi
@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(DetailPhotoViewModel::class)
    fun bindsDetailPhotoViewModel(viewModel: DetailPhotoViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(UserViewModel::class)
    fun bindsUserViewModel(viewModel: UserViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainScreenViewModel::class)
    fun bindsMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchViewModel::class)
    fun bindsSearchPhotosViewModel(viewModel: SearchViewModel): ViewModel
}