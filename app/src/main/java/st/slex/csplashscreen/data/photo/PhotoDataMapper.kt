package st.slex.csplashscreen.data.photo

import st.slex.csplashscreen.data.core.Mapper.DataToUI
import st.slex.csplashscreen.data.core.toImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.ui.core.UIResult
import javax.inject.Inject


interface PhotoDataMapper : DataToUI<RemoteImageModel, UIResult<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {
        override fun map(data: RemoteImageModel): UIResult<ImageModel> =
            UIResult.Success(data = data.toImageModel())

        override fun map(exception: Exception): UIResult<ImageModel> =
            UIResult.Failure(exception = exception)
    }
}