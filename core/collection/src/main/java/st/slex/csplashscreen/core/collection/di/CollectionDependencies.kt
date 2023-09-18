package st.slex.csplashscreen.core.collection.di

import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkClient

interface CollectionDependencies {

    val client: CollectionNetworkClient
}