package com.stslex.csplashscreen.feature.topics.domain

import com.stslex.csplashscreen.feature.topics.data.repository.TopicsRepository
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel
import com.stslex.csplashscreen.feature.topics.domain.model.toTopicsUI

class TopicsInteractorImpl(
    private val repository: TopicsRepository
) : TopicsInteractor {

    override suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<TopicsUIModel> = repository
        .getTopics(
            page = page,
            pageSize = pageSize
        )
        .map { topic ->
            topic.toTopicsUI()
        }
}