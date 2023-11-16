package st.slex.csplashscreen.feature.feature_photo_detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractorImpl
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouterImpl
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCaseImpl
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCaseImpl

@Module
interface ImageDetailModule {

    @Binds
    @ImageDetailScope
    fun bindsInteractor(impl: ImageDetailInteractorImpl): ImageDetailInteractor

    @Binds
    @ImageDetailScope
    fun bindsRouter(impl: ImageDetailRouterImpl): ImageDetailRouter

    @Binds
    @ImageDetailScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ImageDetailViewModel::class)
    fun bindsViewModel(impl: ImageDetailViewModel): ViewModel

    @Binds
    @ImageDetailScope
    fun bindsWallpaperUserCase(impl: WallpaperSetUseCaseImpl): WallpaperSetUseCase

    @Binds
    @ImageDetailScope
    fun bindsDownloadImageUseCase(impl: DownloadImageUseCaseImpl): DownloadImageUseCase
}