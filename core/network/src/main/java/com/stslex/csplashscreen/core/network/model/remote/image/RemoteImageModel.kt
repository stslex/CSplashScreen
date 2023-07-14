package com.stslex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.stslex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel
import com.stslex.csplashscreen.core.network.model.remote.statistic.RemotePhotoStatistics
import com.stslex.csplashscreen.core.network.model.remote.user.RemoteUserModel

@Serializable
class RemoteImageModel(
    @SerialName("id") val id: String,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("width") val width: Int?,
    @SerialName("height") val height: Int?,
    @SerialName("color") val color: String? = "#E0E0E0",
    @SerialName("blur_hash") val blurHash: String?,
    @SerialName("views") val views: Int?,
    @SerialName("downloads") val downloads: Int?,
    @SerialName("likes") val likes: Int?,
    @SerialName("liked_by_user") var likedByUser: Boolean?,
    @SerialName("description") val description: String?,
    @SerialName("alt_description") val altDescription: String?,
    @SerialName("exif") val exif: RemoteExifModel?,
    @SerialName("location") val location: RemoteLocationModel?,
    @SerialName("tags") val tags: List<RemoteTagModel>?,
    @SerialName("current_user_collections") val currentUserCollections: List<RemoteCollectionModel>?,
    @SerialName("sponsorship") val sponsorship: RemoteSponsorship?,
    @SerialName("urls") val urls: RemoteUrlsModel,
    @SerialName("links") val links: RemoteLinksImageModel?,
    @SerialName("user") val user: RemoteUserModel?,
    @SerialName("statistics") val statistics: RemotePhotoStatistics?
)