package com.stslex.csplashscreen.core.network.di

import com.stslex.csplashscreen.core.network.client.NetworkClient
import com.stslex.csplashscreen.core.network.client.NetworkClientImpl
import com.stslex.csplashscreen.core.network.source.interf.CollectionNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.PhotosNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.UserNetworkSource
import com.stslex.csplashscreen.core.network.source.real.CollectionNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.PhotosNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.SearchPhotosNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.TopicsNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.UserNetworkSourceImpl
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
