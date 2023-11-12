package st.slex.csplashscreen.core.network.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.client.NetworkClientImpl
import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkClient
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource
import st.slex.csplashscreen.core.network.source.impl.CollectionNetworkClientImpl
import st.slex.csplashscreen.core.network.source.impl.PhotosNetworkClientImpl
import st.slex.csplashscreen.core.network.source.impl.SearchPhotosNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.impl.TopicsNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.impl.UserNetworkSourceImpl
import javax.inject.Singleton

@Module
interface NetworkClientModule {

    @Binds
    @Singleton
    fun bindsNetworkClient(impl: NetworkClientImpl): NetworkClient

    @Binds
    @Singleton
    fun bindsCollectionNetworkClient(impl: CollectionNetworkClientImpl): CollectionNetworkClient

    @Binds
    @Singleton
    fun bindsPhotosNetworkClientImpl(impl: PhotosNetworkClientImpl): PhotosNetworkClient

    @Binds
    @Singleton
    fun bindsSearchPhotosNetworkSource(impl: SearchPhotosNetworkSourceImpl): SearchPhotosNetworkSource

    @Binds
    @Singleton
    fun bindsTopicsNetworkSource(impl: TopicsNetworkSourceImpl): TopicsNetworkSource

    @Binds
    @Singleton
    fun bindsUserNetworkSource(impl: UserNetworkSourceImpl): UserNetworkSource
}