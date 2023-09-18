package st.slex.csplashscreen.core.collection.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.collection.data.CollectionsRepositoryImpl

@Module
interface CollectionModule {

    @Binds
    fun bindRepository(impl: CollectionsRepositoryImpl): CollectionsRepository
}