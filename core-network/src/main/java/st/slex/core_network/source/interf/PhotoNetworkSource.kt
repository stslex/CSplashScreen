package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.image.RemoteImageModel

interface PhotoNetworkSource {
    suspend fun getSinglePhoto(id: String): RemoteImageModel
}