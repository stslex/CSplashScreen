package st.slex.csplashscreen.feature.collection.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepository
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepositoryImpl
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractorImpl
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouter
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouterImpl
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStore

val moduleFeatureSingleCollection = module {
    factoryOf(::SingleCollectionRepositoryImpl) { bind<SingleCollectionRepository>() }
    factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
    factoryOf(::SingleCollectionRouterImpl) { bind<SingleCollectionRouter>() }
    viewModelOf(::SingleCollectionStore)
}