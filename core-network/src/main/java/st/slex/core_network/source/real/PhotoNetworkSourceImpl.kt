package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.source.interf.PhotoNetworkSource
import st.slex.core_network.utils.ServiceConstants.PATH_DOWNLOAD
import st.slex.core_network.utils.ServiceConstants.PATH_PHOTOS

class PhotoNetworkSourceImpl(
    private val client: NetworkClient
) : PhotoNetworkSource {

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = client.unsplashClient.get {
        url.appendPathSegments(PATH_PHOTOS, id)
    }.body()

    override suspend fun getDownloadedUrl(
        id: String
    ): RemoteDownloadModel = client.unsplashClient.get {
        url.appendPathSegments(PATH_PHOTOS, id, PATH_DOWNLOAD)
    }.body()
}