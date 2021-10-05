package st.slex.csplashscreen.ui.core

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import st.slex.csplashscreen.data.core.DataResult
import st.slex.csplashscreen.data.core.Mapper
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UIResponse {

    suspend fun <D, U> getAndMap(
        flow: Flow<DataResult<D>>,
        mapper: Mapper.DataToUI<D, U>
    ): Flow<U>

    class Base @Inject constructor() : UIResponse {

        override suspend fun <D, U> getAndMap(
            flow: Flow<DataResult<D>>,
            mapper: Mapper.DataToUI<D, U>
        ): Flow<U> =
            callbackFlow {
                flow.startCollecting(mapper) {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        private suspend inline fun <D, T> Flow<DataResult<D>>.startCollecting(
            mapper: Mapper.DataToUI<D, T>,
            crossinline function: (T) -> Unit,
        ) = try {
            collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            Log.e(TAG, exception.message, exception.cause)
        }
    }
}