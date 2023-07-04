package st.slex.core_network.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
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

class ModuleCoreNetwork : AppModule {

    override fun invoke(): Module = module {
        singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
        singleOf(::PhotoNetworkSourceImpl) { bind<PhotoNetworkSource>() }
        singleOf(::UserNetworkSourceImpl) { bind<UserNetworkSource>() }
        singleOf(::TopicsNetworkSourceImpl) { bind<TopicsNetworkSource>() }
        singleOf(::SearchPhotosNetworkSourceImpl) { bind<SearchPhotosNetworkSource>() }
        singleOf(::PagingPhotosNetworkSourceImpl) { bind<PagingPhotosNetworkSource>() }
        singleOf(::CollectionNetworkSourceImpl) { bind<CollectionNetworkSource>() }
    }
}