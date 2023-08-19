package st.slex.csplashscreen.core.collection.data

import st.slex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel

interface CollectionsRepository {

    suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel>
}