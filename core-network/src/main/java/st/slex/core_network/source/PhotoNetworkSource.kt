package st.slex.core_network.source

import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel

interface PhotoNetworkSource {
    suspend fun getSinglePhoto(id: String): RemoteImageModel
    suspend fun getDownloadedUrl(id: String): RemoteDownloadModel
}