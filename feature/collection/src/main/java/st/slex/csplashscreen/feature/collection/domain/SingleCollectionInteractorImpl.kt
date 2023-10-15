package st.slex.csplashscreen.feature.collection.domain

import st.slex.csplashscreen.core.network.model.toDomain
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.feature.collection.data.SingleCollectionRepository
import javax.inject.Inject

class SingleCollectionInteractorImpl @Inject constructor(
    private val repository: SingleCollectionRepository
) : SingleCollectionInteractor {

    override suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = repository
        .getPhotos(
            uuid = uuid,
            page = page,
            pageSize = pageSize
        )
        .toDomain()
}