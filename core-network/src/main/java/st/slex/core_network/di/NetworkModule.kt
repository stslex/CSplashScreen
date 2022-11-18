package st.slex.core_network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.client.NetworkClientImpl
import st.slex.core_network.source.interf.CollectionNetworkSource
import st.slex.core_network.source.interf.PagingPhotosNetworkSource
import st.slex.core_network.source.interf.PhotoNetworkSource
import st.slex.core_network.source.interf.SearchPhotosNetworkSource
import st.slex.core_network.source.interf.TopicsNetworkSource
import st.slex.core_network.source.interf.UserNetworkSource
import st.slex.core_network.source.real.CollectionNetworkSourceImpl
import st.slex.core_network.source.real.PagingPhotosNetworkSourceImpl
import st.slex.core_network.source.real.PhotoNetworkSourceImpl
import st.slex.core_network.source.real.SearchPhotosNetworkSourceImpl
import st.slex.core_network.source.real.TopicsNetworkSourceImpl
import st.slex.core_network.source.real.UserNetworkSourceImpl

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {

    @Binds
    fun bindsNetworkClient(clientImpl: NetworkClientImpl): NetworkClient

    @Binds
    fun bindsPhotoNetworkSource(source: PhotoNetworkSourceImpl): PhotoNetworkSource

    @Binds
    fun bindUserNetworkSource(source: UserNetworkSourceImpl): UserNetworkSource

    @Binds
    fun bindsTopicsNetworkSource(source: TopicsNetworkSourceImpl): TopicsNetworkSource

    @Binds
    fun bindsPhotosPagingSource(source: PagingPhotosNetworkSourceImpl): PagingPhotosNetworkSource

    @Binds
    fun bindSearchPhotosSource(source: SearchPhotosNetworkSourceImpl): SearchPhotosNetworkSource

    @Binds
    fun bindsCollectionNetworkSource(source: CollectionNetworkSourceImpl): CollectionNetworkSource
}