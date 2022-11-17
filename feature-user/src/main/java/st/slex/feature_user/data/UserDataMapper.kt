package st.slex.feature_user.data

import st.slex.core.Mapper
import st.slex.core.Resource
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_network.model.map
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