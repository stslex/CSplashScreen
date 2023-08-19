package st.slex.csplashscreen.feature.collection.domain

import st.slex.csplashscreen.core.network.model.ui.ImageModel

interface SingleCollectionInteractor {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>
}