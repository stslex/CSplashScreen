package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemoteLinksImageModel(
    @SerializedName("self") val self: String,
    @SerializedName("html") val html: String,
    @SerializedName("download") val download: String,
    @SerializedName("download_location") val download_location: String
)