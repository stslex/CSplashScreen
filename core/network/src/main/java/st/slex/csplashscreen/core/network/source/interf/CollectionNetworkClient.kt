package st.slex.csplashscreen.core.network.source.interf

import st.slex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel

interface CollectionNetworkClient {

    suspend fun getCollections(page: Int, pageSize: Int): List<RemoteCollectionModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel>
}