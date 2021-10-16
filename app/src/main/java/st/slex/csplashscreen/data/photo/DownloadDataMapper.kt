package st.slex.csplashscreen.data.photo

import st.slex.csplashscreen.core.Mapper
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.core.toDownloadModel
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.ui.DownloadModel
import javax.inject.Inject

interface DownloadDataMapper : Mapper.ToUI<RemoteDownloadModel, Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): Resource<DownloadModel> =
            Resource.Success(data.toDownloadModel())

        override fun map(exception: Exception): Resource<DownloadModel> =
            Resource.Failure(exception)

        override fun map(): Resource<DownloadModel> = Resource.Loading
    }
}