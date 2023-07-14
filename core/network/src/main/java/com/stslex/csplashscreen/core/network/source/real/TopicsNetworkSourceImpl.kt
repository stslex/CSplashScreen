package com.stslex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import com.stslex.csplashscreen.core.network.client.NetworkClient
import com.stslex.csplashscreen.core.network.model.remote.topics.RemoteTopicsModel
import com.stslex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import com.stslex.csplashscreen.core.network.utils.ServiceConstants
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE

class TopicsNetworkSourceImpl(
    private val client: NetworkClient
) : TopicsNetworkSource {

    override suspend fun getTopics(
        page: Int
    ): List<RemoteTopicsModel> = client.unsplashClient.get {
        url.appendPathSegments(ServiceConstants.PATH_TOPICS)
        parameter(PARAMETER_PAGE, page)
    }.body()
}