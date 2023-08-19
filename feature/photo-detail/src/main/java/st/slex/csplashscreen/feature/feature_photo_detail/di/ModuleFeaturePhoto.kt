package st.slex.csplashscreen.feature.feature_photo_detail.di

import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractorImpl
import st.slex.csplashscreen.feature.feature_photo_detail.ui.DetailPhotoViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCaseImpl
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moduleFeaturePhoto = module {
    viewModelOf(::DetailPhotoViewModel)
    factoryOf(::ImageDetailInteractorImpl) { bind<ImageDetailInteractor>() }
    factoryOf(::DownloadImageUseCaseImpl) { bind<DownloadImageUseCase>() }
    factory<WallpaperSetUseCase> {
        WallpaperSetUseCaseImpl(context = androidContext())
    }
}