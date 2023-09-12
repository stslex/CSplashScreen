package st.slex.csplashscreen.core.photos.di

import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient

interface PhotosDependencies {

    val photosClient: PhotosNetworkClient
}