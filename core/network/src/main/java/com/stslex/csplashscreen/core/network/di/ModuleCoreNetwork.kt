package com.stslex.csplashscreen.core.network.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.network.client.NetworkClient
import com.stslex.csplashscreen.core.network.client.NetworkClientImpl
import com.stslex.csplashscreen.core.network.source.interf.CollectionNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.PagingPhotosNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.PhotoNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import com.stslex.csplashscreen.core.network.source.interf.UserNetworkSource
import com.stslex.csplashscreen.core.network.source.real.CollectionNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.PagingPhotosNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.PhotoNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.SearchPhotosNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.TopicsNetworkSourceImpl
import com.stslex.csplashscreen.core.network.source.real.UserNetworkSourceImpl

val moduleCoreNetwork = module {
    singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
    singleOf(::PhotoNetworkSourceImpl) { bind<PhotoNetworkSource>() }
    singleOf(::UserNetworkSourceImpl) { bind<UserNetworkSource>() }
    singleOf(::TopicsNetworkSourceImpl) { bind<TopicsNetworkSource>() }
    singleOf(::SearchPhotosNetworkSourceImpl) { bind<SearchPhotosNetworkSource>() }
    singleOf(::PagingPhotosNetworkSourceImpl) { bind<PagingPhotosNetworkSource>() }
    singleOf(::CollectionNetworkSourceImpl) { bind<CollectionNetworkSource>() }
}