package st.slex.csplashscreen.core.network.client

import io.ktor.client.HttpClient

interface NetworkClient {
    val client: HttpClient
    val unsplashClient: HttpClient
}
