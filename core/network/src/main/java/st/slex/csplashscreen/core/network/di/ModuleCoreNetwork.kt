package st.slex.csplashscreen.core.network.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.client.NetworkClientImpl
import st.slex.csplashscreen.core.network.source.impl.CollectionNetworkClientImpl
import st.slex.csplashscreen.core.network.source.impl.PhotosNetworkClientImpl
import st.slex.csplashscreen.core.network.source.impl.SearchPhotosNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.impl.TopicsNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.impl.UserNetworkSourceImpl
import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkClient
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource

val moduleCoreNetwork = module {
    singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
    singleOf(::CollectionNetworkClientImpl) { bind<CollectionNetworkClient>() }
    singleOf(::PhotosNetworkClientImpl) { bind<PhotosNetworkClient>() }
    singleOf(::SearchPhotosNetworkSourceImpl) { bind<SearchPhotosNetworkSource>() }
    singleOf(::TopicsNetworkSourceImpl) { bind<TopicsNetworkSource>() }
    singleOf(::UserNetworkSourceImpl) { bind<UserNetworkSource>() }
}