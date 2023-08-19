package st.slex.csplashscreen.feature.home.domain

import st.slex.csplashscreen.core.network.model.ui.CollectionDomainModel
import st.slex.csplashscreen.core.network.model.ui.ImageModel

interface MainScreenInteractor {

    suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel>
}