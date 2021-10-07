package st.slex.csplashscreen.data.photo

import st.slex.csplashscreen.data.core.Mapper
import st.slex.csplashscreen.data.core.toDownloadModel
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.ui.DownloadModel
import st.slex.csplashscreen.ui.core.UIResult
import javax.inject.Inject

interface DownloadDataMapper : Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {
        override fun map(data: RemoteDownloadModel): UIResult<DownloadModel> =
            UIResult.Success(data.toDownloadModel())

        override fun map(exception: Exception): UIResult<DownloadModel> =
            UIResult.Failure(exception)
    }
}