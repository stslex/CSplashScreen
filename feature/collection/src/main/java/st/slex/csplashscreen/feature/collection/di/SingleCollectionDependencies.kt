package st.slex.csplashscreen.feature.collection.di

import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient

interface SingleCollectionDependencies {

    val client: PhotosNetworkClient
}