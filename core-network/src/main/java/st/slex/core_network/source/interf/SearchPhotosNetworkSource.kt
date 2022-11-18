package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.image.RemoteImageSearchModel

interface SearchPhotosNetworkSource {

    suspend fun searchPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): RemoteImageSearchModel
}