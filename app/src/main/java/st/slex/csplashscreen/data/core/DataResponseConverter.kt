package st.slex.csplashscreen.data.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import st.slex.csplashscreen.core.Resource
import javax.inject.Inject

interface DataResponseConverter {

    suspend fun <T> convert(response: Response<T>): Flow<Resource<T>>

    class Base @Inject constructor() : DataResponseConverter {

        override suspend fun <T> convert(response: Response<T>): Flow<Resource<T>> = flow {
            try {
                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!))
                } else emit(Resource.Failure(HttpException(response)))
            } catch (exception: Exception) {
                emit(Resource.Failure(exception))
            }
        }
    }
}