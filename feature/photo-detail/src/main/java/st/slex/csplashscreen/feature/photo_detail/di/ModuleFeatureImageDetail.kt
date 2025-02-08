package st.slex.csplashscreen.feature.photo_detail.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.photo_detail.domain.interactor.ImageDetailInteractorImpl
import st.slex.csplashscreen.feature.photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.photo_detail.navigation.ImageDetailRouterImpl
import st.slex.csplashscreen.feature.photo_detail.ui.presenter.ImageDetailStore
import st.slex.csplashscreen.feature.photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.photo_detail.ui.utils.DownloadImageUseCaseImpl
import st.slex.csplashscreen.feature.photo_detail.ui.utils.WallpaperSetUseCase
import st.slex.csplashscreen.feature.photo_detail.ui.utils.WallpaperSetUseCaseImpl

val moduleFeatureImageDetail = module {
    factoryOf(::ImageDetailInteractorImpl) { bind<ImageDetailInteractor>() }
    factoryOf(::ImageDetailRouterImpl) { bind<ImageDetailRouter>() }
    viewModelOf(::ImageDetailStore)
    factoryOf(::WallpaperSetUseCaseImpl) { bind<WallpaperSetUseCase>() }
    factoryOf(::DownloadImageUseCaseImpl) { bind<DownloadImageUseCase>() }
}