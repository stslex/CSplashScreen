package st.slex.csplashscreen.data.photo

import st.slex.csplashscreen.data.core.Mapper.DataToUI
import st.slex.csplashscreen.data.core.toImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import javax.inject.Inject


class PhotoDataMapper @Inject constructor() :
    DataToUI<RemoteImageModel, ImageModel?> {

    override fun map(data: RemoteImageModel): ImageModel =
        data.toImageModel()

    override fun map(exception: Exception): ImageModel? = null
}