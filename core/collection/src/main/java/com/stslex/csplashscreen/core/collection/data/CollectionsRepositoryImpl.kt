package com.stslex.csplashscreen.core.collection.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.stslex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel
import com.stslex.csplashscreen.core.network.source.interf.CollectionNetworkSource

class CollectionsRepositoryImpl(
    private val networkSource: CollectionNetworkSource
) : CollectionsRepository {

    override suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = withContext(Dispatchers.IO) {
        networkSource
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
        networkSource
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