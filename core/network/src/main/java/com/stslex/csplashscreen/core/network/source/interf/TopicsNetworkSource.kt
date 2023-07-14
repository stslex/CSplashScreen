package com.stslex.csplashscreen.core.network.source.interf

import com.stslex.csplashscreen.core.network.model.remote.topics.RemoteTopicsModel

interface TopicsNetworkSource {

    suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<RemoteTopicsModel>
}