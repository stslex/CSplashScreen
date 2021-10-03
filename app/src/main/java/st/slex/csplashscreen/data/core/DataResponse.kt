package st.slex.csplashscreen.data.core

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DataResponse {

    fun <T> create(response: Response<T>): Flow<DataResult<T>>

    class Base @Inject constructor() : DataResponse {

        override fun <T> create(response: Response<T>): Flow<DataResult<T>> =
            callbackFlow {
                response.responseEvent {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private inline fun <T> Response<T>.responseEvent(
            crossinline function: (DataResult<T>) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    function(DataResult.Success(it))
                }
            } else {
                function(DataResult.Failure(Exception(errorBody().toString())))
            }
        } catch (exception: Exception) {
            function(DataResult.Failure(exception))
        }
    }
}