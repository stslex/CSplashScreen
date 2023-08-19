package st.slex.csplashscreen.core.collection.di

import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.collection.data.CollectionsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCoreCollection {

    val moduleCoreCollection = module {
        singleOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
    }
}
