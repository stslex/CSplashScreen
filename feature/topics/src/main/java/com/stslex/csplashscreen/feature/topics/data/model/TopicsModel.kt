package com.stslex.csplashscreen.feature.topics.data.model

import com.stslex.csplashscreen.core.network.model.ui.collection.LinksCollectionModel
import com.stslex.csplashscreen.core.network.model.ui.ImageModel
import com.stslex.csplashscreen.core.network.model.ui.user.UserModel

data class TopicsModel(
    val id: String,
    val slug: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val updatedAt: String,
    val startsAt: String,
    val endsAt: String,
    val onlySubmissionsAfter: String,
    val featured: String,
    val totalPhotos: String,
    val links: LinksCollectionModel,
    val status: String,
    val owners: List<UserModel>,
    val coverPhoto: ImageModel,
    val previewPhotos: List<PreviewPhotosModel>
)