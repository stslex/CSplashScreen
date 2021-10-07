package st.slex.csplashscreen.data.user

import st.slex.csplashscreen.data.core.Mapper
import st.slex.csplashscreen.data.core.toUserModel
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.data.model.ui.user.UserModel
import st.slex.csplashscreen.ui.core.UIResult
import javax.inject.Inject

interface UserDataMapper : Mapper.DataToUI<RemoteUserModel, UIResult<UserModel>> {

    class Base @Inject constructor() : UserDataMapper {

        override fun map(data: RemoteUserModel): UIResult<UserModel> =
            UIResult.Success(data = data.toUserModel())

        override fun map(exception: Exception): UIResult<UserModel> =
            UIResult.Failure(exception = exception)
    }
}