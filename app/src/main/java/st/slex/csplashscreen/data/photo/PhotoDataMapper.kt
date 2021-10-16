package st.slex.csplashscreen.data.photo

import st.slex.csplashscreen.core.Mapper
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.core.toImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import javax.inject.Inject


interface PhotoDataMapper : Mapper.ToUI<RemoteImageModel, Resource<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): Resource<ImageModel> =
            Resource.Success(data = data.toImageModel())

        override fun map(exception: Exception): Resource<ImageModel> =
            Resource.Failure(exception = exception)

        override fun map(): Resource<ImageModel> = Resource.Loading
    }
}