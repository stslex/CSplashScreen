package st.slex.core_photos.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.core_photos.data.PhotosPagingSource
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.PhotosRepositoryImpl

class ModuleCorePhotos : AppModule {

    override val module: Module = module {
        singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
        singleOf(PhotosPagingSource::Factory)
    }
}