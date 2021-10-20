package st.slex.csplashscreen.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.di.key.ViewModelKey
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionViewModel
import st.slex.csplashscreen.ui.screens.detail.DetailPhotoViewModel
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import st.slex.csplashscreen.ui.screens.raw_image.RawImageViewModel
import st.slex.csplashscreen.ui.screens.search_photos.SearchViewModel
import st.slex.csplashscreen.ui.screens.topics.TopicsViewModel
import st.slex.csplashscreen.ui.screens.user.UserViewModel

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

    @IntoMap
    @Binds
    @ViewModelKey(TopicsViewModel::class)
    fun bindsTopicsViewModel(viewModel: TopicsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(RawImageViewModel::class)
    fun bindsRawImageViewModel(viewModel: RawImageViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SingleCollectionViewModel::class)
    fun bindsSingleCollectionViewModel(viewModelKey: SingleCollectionViewModel): ViewModel
}