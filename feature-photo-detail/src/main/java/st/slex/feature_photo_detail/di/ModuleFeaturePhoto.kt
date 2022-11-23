package st.slex.feature_photo_detail.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.data.PhotoRepositoryImpl
import st.slex.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.feature_photo_detail.navigation.ImageDetailRouterImpl
import st.slex.feature_photo_detail.ui.DetailPhotoViewModel
import st.slex.feature_photo_detail.ui.DownloadImageUseCase

class ModuleFeaturePhoto : AppModule {

    override fun invoke(): Module = module {
        singleOf(::PhotoRepositoryImpl) { bind<PhotoRepository>() }
        factoryOf(::ImageDetailRouterImpl) { bind<ImageDetailRouter>() }
        viewModelOf(::DetailPhotoViewModel)
        singleOf(DownloadImageUseCase::Base) { bind<DownloadImageUseCase>() }
    }
}