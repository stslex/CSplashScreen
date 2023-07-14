package com.stslex.csplashscreen.feature.topics.data.repository

import com.stslex.csplashscreen.feature.topics.data.model.TopicsModel

interface TopicsRepository {

    suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<TopicsModel>
}