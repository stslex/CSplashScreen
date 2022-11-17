package st.slex.feature_photo_detail.data

import st.slex.core.Mapper
import st.slex.core.Resource
import st.slex.core.Resource.Failure
import st.slex.core.Resource.Loading
import st.slex.core.Resource.Success
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.map
import javax.inject.Inject

interface DownloadDataMapper : Mapper.ToUI<RemoteDownloadModel, Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): Resource<DownloadModel> = Success(data.map())

        override fun map(exception: Exception): Resource<DownloadModel> = Failure(exception)

        override fun map(): Resource<DownloadModel> = Loading
    }
}