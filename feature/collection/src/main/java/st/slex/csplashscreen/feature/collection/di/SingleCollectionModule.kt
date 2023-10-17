package st.slex.csplashscreen.feature.collection.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepository
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepositoryImpl
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractorImpl
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouter
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouterImpl
import st.slex.csplashscreen.feature.collection.ui.SingleCollectionViewModel
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStoreImpl

@Module
interface SingleCollectionModule {

    @Binds
    @SingleCollectionScope
    fun bindsRepository(impl: SingleCollectionRepositoryImpl): SingleCollectionRepository

    @Binds
    @SingleCollectionScope
    fun bindsInteractor(impl: SingleCollectionInteractorImpl): SingleCollectionInteractor

    @Binds
    @SingleCollectionScope
    fun bindsStore(impl: SingleCollectionStoreImpl): SingleCollectionStore

    @Binds
    @SingleCollectionScope
    fun bindRouter(impl: SingleCollectionRouterImpl): SingleCollectionRouter

    @Binds
    @IntoMap
    @ViewModelKey(SingleCollectionViewModel::class)
    fun bindsViewModel(impl: SingleCollectionViewModel): ViewModel

    @Binds
    @SingleCollectionScope
    fun bindsFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}