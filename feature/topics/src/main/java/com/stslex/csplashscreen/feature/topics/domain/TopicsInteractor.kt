package com.stslex.csplashscreen.feature.topics.domain

import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel

interface TopicsInteractor {

    suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<TopicsUIModel>
}
