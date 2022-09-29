package st.slex.csplashscreen.data.photo

import st.slex.core.Mapper
import st.slex.core.Resource.Failure
import st.slex.core.Resource.Loading
import st.slex.core.Resource.Success
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.ui.DownloadModel
import st.slex.feature_main.map
import javax.inject.Inject

interface DownloadDataMapper :
    Mapper.ToUI<RemoteDownloadModel, st.slex.core.Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): st.slex.core.Resource<DownloadModel> =
            Success(data.map())

        override fun map(exception: Exception): st.slex.core.Resource<DownloadModel> =
            Failure(exception)

        override fun map(): st.slex.core.Resource<DownloadModel> = Loading
    }
}