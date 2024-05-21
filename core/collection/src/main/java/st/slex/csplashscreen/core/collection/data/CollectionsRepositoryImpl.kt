package st.slex.csplashscreen.core.collection.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkClient

class CollectionsRepositoryImpl(
    private val client: CollectionNetworkClient
) : CollectionsRepository {

    override suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = withContext(Dispatchers.IO) {
        client
            .getCollections(
                page = page,
                pageSize = pageSize
            )
            .filterPaid()
    }

    override suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = withContext(Dispatchers.IO) {
        client
            .getUserCollections(
                username = username,
                page = page,
                pageSize = pageSize
            )
            .filterPaid()
    }

    private fun List<RemoteCollectionModel>.filterPaid(): List<RemoteCollectionModel> =
        filterNot { collection ->
            collection.user?.firstName?.lowercase() == PRIVATE_COLLECTION.lowercase()
        }

    companion object {
        private const val PRIVATE_COLLECTION = "Unsplash+"
    }
}