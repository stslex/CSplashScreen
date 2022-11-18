package st.slex.core_network.model.remote.collection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLinksCollectionModel(
    @SerialName("self") val self: String,
    @SerialName("html") val html: String,
    @SerialName("photos") val photos: String
)