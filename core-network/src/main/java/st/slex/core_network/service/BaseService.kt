package st.slex.core_network.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import st.slex.core.Resource

interface BaseService {

    companion object {

        fun <T, S : BaseService> S.makeRequest(
            request: suspend S.() -> Response<T>
        ): Flow<Resource<T>> = flow {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body() ?: throw HttpException(response)
                emit(Resource.Success(body))
            } else {
                emit(Resource.Failure(HttpException(response)))
            }
        }.catch { throwable ->
            emit(Resource.Failure(Exception(throwable)))
        }
    }
}