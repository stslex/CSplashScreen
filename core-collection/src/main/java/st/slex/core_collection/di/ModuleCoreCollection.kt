package st.slex.core_collection.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.core_collection.data.CollectionsPagingSource
import st.slex.core_collection.data.CollectionsRepository
import st.slex.core_collection.data.CollectionsRepositoryImpl

class ModuleCoreCollection : AppModule {
    override val module = module {
        singleOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
        singleOf(CollectionsPagingSource::Factory)
    }
}