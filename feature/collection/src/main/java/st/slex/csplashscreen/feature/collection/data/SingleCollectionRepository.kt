package st.slex.csplashscreen.feature.collection.data

import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface SingleCollectionRepository {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>
}