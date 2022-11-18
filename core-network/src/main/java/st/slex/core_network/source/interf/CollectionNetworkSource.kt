package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.collection.RemoteCollectionModel

interface CollectionNetworkSource {

    suspend fun getCollections(page: Int, pageSize: Int): List<RemoteCollectionModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel>
}