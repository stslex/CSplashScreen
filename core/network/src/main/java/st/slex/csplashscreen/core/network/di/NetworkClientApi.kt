package st.slex.csplashscreen.core.network.di

import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkClient
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource

interface NetworkClientApi {

    val collectionsClient: CollectionNetworkClient

    val photosClient: PhotosNetworkClient

    val searchClient: SearchPhotosNetworkSource

    val topicsClient: TopicsNetworkSource

    val userClient: UserNetworkSource
}