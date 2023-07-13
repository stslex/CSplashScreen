package com.stslex.csplashscreen.feature.user.domain

import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import st.slex.core_network.model.mapToDomain
import st.slex.core_network.model.toDomain
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import com.stslex.csplashscreen.feature.user.data.UserRepository

class UserInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository,
    private val userRepository: UserRepository
) : UserInteractor {

    override suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel> = collectionsRepository
        .getUserCollections(
            username = username,
            page = page,
            pageSize = pageSize
        )
        .mapToDomain()

    override suspend fun getUserLikePhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = photosRepository
        .getUserLikePhotos(
            username = username,
            page = page,
            pageSize = pageSize
        )
        .toDomain()

    override suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = photosRepository
        .getUserPhotos(
            username = username,
            page = page,
            pageSize = pageSize
        )
        .toDomain()

    override fun getUser(
        username: String
    ): Flow<UserModel> = userRepository
        .getUser(
            username = username
        )
        .map { user ->
            user.toDomain()
        }
}