package com.stslex.csplashscreen.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import st.slex.core_network.BuildConfig

class NetworkClientImpl : NetworkClient {

    @OptIn(ExperimentalSerializationApi::class)
    override val client: HttpClient
        get() = HttpClient(Android) {

            expectSuccess = true

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

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

            install(HttpCache)
        }

    override val unsplashClient: HttpClient
        get() = client.config {
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

    companion object {
        private const val HOST_URL = "api.unsplash.com"
        private const val HEADER_AUTH = "Authorization"
        private const val HEADER_AUTH_FIELD = "Client-ID"
    }
}