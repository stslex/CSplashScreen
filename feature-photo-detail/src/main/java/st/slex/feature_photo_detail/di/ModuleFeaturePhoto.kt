package st.slex.feature_photo_detail.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.data.PhotoRepositoryImpl
import st.slex.feature_photo_detail.ui.DetailPhotoViewModel
import st.slex.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.feature_photo_detail.ui.utils.DownloadImageUseCaseImpl
import st.slex.feature_photo_detail.ui.utils.WallpaperSetUseCase
import st.slex.feature_photo_detail.ui.utils.WallpaperSetUseCaseImpl

val moduleFeaturePhoto = module {
    singleOf(::PhotoRepositoryImpl) { bind<PhotoRepository>() }
    viewModelOf(::DetailPhotoViewModel)
    factoryOf(::DownloadImageUseCaseImpl) { bind<DownloadImageUseCase>() }
    factory<WallpaperSetUseCase> {
        WallpaperSetUseCaseImpl(context = androidContext())
    }
}