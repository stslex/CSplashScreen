package st.slex.core_network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.client.NetworkClientImpl
import st.slex.core_network.source.PhotoNetworkSource
import st.slex.core_network.source.PhotoNetworkSourceImpl
import st.slex.core_network.source.UserNetworkSource
import st.slex.core_network.source.UserNetworkSourceImpl

@InstallIn(SingletonComponent::class)
@Module
interface NetworkModule {

    @Binds
    fun bindsNetworkClient(clientImpl: NetworkClientImpl): NetworkClient

    @Binds
    fun bindsPhotoNetworkSource(source: PhotoNetworkSourceImpl): PhotoNetworkSource

    @Binds
    fun bindUserNetworkSource(source: UserNetworkSourceImpl): UserNetworkSource
}