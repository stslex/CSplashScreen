package st.slex.csplashscreen.feature.search.di

import androidx.room.Room
import st.slex.csplashscreen.feature.search.data.database.SearchDao
import st.slex.csplashscreen.feature.search.data.database.SearchDatabase
import st.slex.csplashscreen.feature.search.data.repository.SearchRepository
import st.slex.csplashscreen.feature.search.data.repository.SearchRepositoryImpl
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractorImpl
import st.slex.csplashscreen.feature.search.ui.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleFeatureSearchPhotos = module {
    singleOf(::SearchRepositoryImpl) { bind<SearchRepository>() }
    factoryOf(::SearchPhotosInteractorImpl) { bind<SearchPhotosInteractor>() }
    viewModelOf(::SearchViewModel)

    single<SearchDao> {
        Room
            .databaseBuilder(
                androidContext(),
                SearchDatabase::class.java,
                "search.db"
            )
            .build()
            .dao
    }
}