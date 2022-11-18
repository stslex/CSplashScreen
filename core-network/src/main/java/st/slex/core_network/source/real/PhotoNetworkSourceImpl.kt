package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.service.ServiceConstants.GET_DOWNLOAD
import st.slex.core_network.service.ServiceConstants.GET_PHOTOS
import st.slex.core_network.source.interf.PhotoNetworkSource
import javax.inject.Inject

class PhotoNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : PhotoNetworkSource {

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = client.unsplashClient.get {
        url.appendPathSegments(GET_PHOTOS, id)
    }.body()

    override suspend fun getDownloadedUrl(
        id: String
    ): RemoteDownloadModel = client.unsplashClient.get {
        url.appendPathSegments(GET_PHOTOS, id, GET_DOWNLOAD)
    }.body()
}