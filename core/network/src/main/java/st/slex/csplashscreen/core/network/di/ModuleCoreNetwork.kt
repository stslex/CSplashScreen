package st.slex.csplashscreen.core.network.di

import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.client.NetworkClientImpl
import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkSource
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkSource
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource
import st.slex.csplashscreen.core.network.source.real.CollectionNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.real.PhotosNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.real.SearchPhotosNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.real.TopicsNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.real.UserNetworkSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCoreNetwork {

    val moduleCoreNetwork = module {
        singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
        singleOf(::UserNetworkSourceImpl) { bind<UserNetworkSource>() }
        singleOf(::TopicsNetworkSourceImpl) { bind<TopicsNetworkSource>() }
        singleOf(::SearchPhotosNetworkSourceImpl) { bind<SearchPhotosNetworkSource>() }
        singleOf(::PhotosNetworkSourceImpl) { bind<PhotosNetworkSource>() }
        singleOf(::CollectionNetworkSourceImpl) { bind<CollectionNetworkSource>() }
    }
}
