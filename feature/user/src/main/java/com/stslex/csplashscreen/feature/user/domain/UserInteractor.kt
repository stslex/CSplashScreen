package com.stslex.csplashscreen.feature.user.domain

import kotlinx.coroutines.flow.Flow
import com.stslex.csplashscreen.core.network.model.ui.CollectionDomainModel
import com.stslex.csplashscreen.core.network.model.ui.ImageModel
import com.stslex.csplashscreen.core.network.model.ui.user.UserModel

interface UserInteractor {

    fun getUser(username: String): Flow<UserModel>

    suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getUserLikePhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel>
}