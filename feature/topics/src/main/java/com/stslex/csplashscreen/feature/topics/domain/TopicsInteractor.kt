package com.stslex.csplashscreen.feature.topics.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel

interface TopicsInteractor {
    val topics: Flow<PagingData<TopicsUIModel>>
}
