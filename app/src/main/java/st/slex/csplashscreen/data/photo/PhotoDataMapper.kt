package st.slex.csplashscreen.data.photo

import st.slex.core.Resource
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.feature_main.map
import javax.inject.Inject


interface PhotoDataMapper : st.slex.core.Mapper.ToUI<RemoteImageModel, Resource<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): Resource<ImageModel> =
            Resource.Success(data = data.map())

        override fun map(exception: Exception): Resource<ImageModel> =
            Resource.Failure(exception = exception)

        override fun map(): Resource<ImageModel> = Resource.Loading
    }
}