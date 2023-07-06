package st.slex.feature_search_photos.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_search_photos.data.SearchPagingSource
import st.slex.feature_search_photos.data.SearchRepository
import st.slex.feature_search_photos.data.SearchRepositoryImpl
import st.slex.feature_search_photos.domain.SearchPhotosInteractor
import st.slex.feature_search_photos.domain.SearchPhotosInteractorImpl
import st.slex.feature_search_photos.ui.SearchViewModel

val moduleFeatureSearchPhotos = module {
    singleOf(::SearchRepositoryImpl) { bind<SearchRepository>() }
    singleOf(SearchPagingSource::Factory)
    viewModelOf(::SearchViewModel)
    factoryOf(::SearchPhotosInteractorImpl) { bind<SearchPhotosInteractor>() }
}