package st.slex.csplashscreen.feature.collection.di

import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepository
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepositoryImpl
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractorImpl
import st.slex.csplashscreen.feature.collection.ui.SingleCollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val singleCollectionModule = module {
    factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
    singleOf(::SingleCollectionRepositoryImpl) { bind<SingleCollectionRepository>() }
    viewModelOf(::SingleCollectionViewModel)
}