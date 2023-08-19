package st.slex.csplashscreen.core.photos.di

import st.slex.csplashscreen.core.photos.data.PhotosRepository
import st.slex.csplashscreen.core.photos.data.PhotosRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCorePhotos {

    val moduleCorePhotos = module {
        singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
    }
}
