package st.slex.csplashscreen.data.user

import st.slex.csplashscreen.core.Mapper
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.core.map
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.data.model.ui.user.UserModel
import javax.inject.Inject

interface UserDataMapper : Mapper.ToUI<RemoteUserModel, Resource<UserModel>> {

    class Base @Inject constructor() : UserDataMapper {

        override fun map(data: RemoteUserModel): Resource<UserModel> =
            Resource.Success(data = data.map())

        override fun map(exception: Exception): Resource<UserModel> =
            Resource.Failure(exception = exception)

        override fun map(): Resource<UserModel> = Resource.Loading
    }
}