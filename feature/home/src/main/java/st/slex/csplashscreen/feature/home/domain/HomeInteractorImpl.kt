package st.slex.csplashscreen.feature.home.domain

import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.network.model.mapToDomain
import st.slex.csplashscreen.core.network.model.toDomain
import st.slex.csplashscreen.core.network.model.ui.CollectionDomainModel
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.core.photos.data.PhotosRepository

class HomeInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository
) : HomeInteractor {

    override suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel> = collectionsRepository
        .getAllCollections(
            page = page,
            pageSize = pageSize
        )
        .mapToDomain()

    override suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<ImageModel> = photosRepository
        .getAllPhotos(
            page = page,
            pageSize = pageSize
        )
        .toDomain()
        .also {
            Logger.tag("DEBUG_HOME").d("getAllPhotos: $it")
        }
}