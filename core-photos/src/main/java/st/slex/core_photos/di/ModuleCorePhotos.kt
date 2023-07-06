package st.slex.core_photos.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core_photos.data.PhotosPagingSource
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.PhotosRepositoryImpl

val moduleCorePhotos = module {
    singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
    singleOf(PhotosPagingSource::Factory)
}