package st.slex.csplashscreen.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.network.BuildConfig

class NetworkClientImpl(
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

    @OptIn(ExperimentalSerializationApi::class)
    fun HttpClientConfig<AndroidEngineConfig>.setupNegotiation() {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
    }

    fun HttpClientConfig<AndroidEngineConfig>.setupDefaultRequest() {
        defaultRequest {
            url {
                host = HOST_URL
                protocol = URLProtocol.HTTPS
            }
            headers {
                append(
                    HEADER_AUTH,
                    "$HEADER_AUTH_FIELD ${BuildConfig.API_KEY}"
                )
            }
        }
    }

    fun HttpClientConfig<AndroidEngineConfig>.setupLogging() {
        install(Logging) {
            logger = when (st.slex.csplashscreen.core.core.BuildConfig.DEBUG) {
                true -> Logger.ANDROID
                false -> Logger.EMPTY
            }
            level = when (st.slex.csplashscreen.core.core.BuildConfig.DEBUG) {
                true -> LogLevel.ALL
                false -> LogLevel.NONE
            }
        }
    }

    companion object {
        private const val HOST_URL = "api.unsplash.com"
        private const val HEADER_AUTH = "Authorization"
        private const val HEADER_AUTH_FIELD = "Client-ID"
    }
}
