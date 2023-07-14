package com.stslex.csplashscreen.core.network.source.interf

import com.stslex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel

interface CollectionNetworkSource {

    suspend fun getCollections(page: Int, pageSize: Int): List<RemoteCollectionModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel>
}