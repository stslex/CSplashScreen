package st.slex.csplashscreen.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cache.HttpCache
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.network.client.NetworkClientBuilder.setupDefaultRequest
import st.slex.csplashscreen.core.network.client.NetworkClientBuilder.setupLogging
import st.slex.csplashscreen.core.network.client.NetworkClientBuilder.setupNegotiation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkClientImpl @Inject constructor(
    private val appDispatcher: AppDispatcher
) : NetworkClient {

    override suspend fun <T> request(
        request: suspend HttpClient.() -> T
    ): T = withContext(appDispatcher.io) {
        request(client)
    }

    private val client: HttpClient = HttpClient(Android) {
        setupNegotiation()
        install(HttpCache)// TODO Not working?
        setupLogging()
        expectSuccess = true
        setupDefaultRequest()
    }
}
