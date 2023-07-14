package com.stslex.csplashscreen.feature.topics.data.repository

import com.stslex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import com.stslex.csplashscreen.feature.topics.data.model.TopicsModel
import com.stslex.csplashscreen.feature.topics.data.model.toTopicsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopicsRepositoryImpl(
    private val networkSource: TopicsNetworkSource
) : TopicsRepository {

    override suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<TopicsModel> = withContext(Dispatchers.IO) {
        networkSource
            .getTopics(
                page = page,
                pageSize = pageSize
            )
            .map { topic ->
                topic.toTopicsModel()
            }
    }
}