package st.slex.csplashscreen.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get

interface NetworkClient {

    suspend fun <T> request(request: suspend HttpClient.() -> T): T
}

internal suspend inline fun <reified T> NetworkClient.get(
    crossinline builder: suspend HttpRequestBuilder.() -> Unit
): T = request {
    get {
        builder()
    }.body()
}
