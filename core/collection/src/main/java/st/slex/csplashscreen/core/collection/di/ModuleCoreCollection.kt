package st.slex.csplashscreen.core.collection.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.collection.data.CollectionsRepositoryImpl

val moduleCoreCollection = module {
    factoryOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
}