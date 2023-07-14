package com.stslex.csplashscreen.feature.topics.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.stslex.csplashscreen.feature.topics.data.model.TopicsModel
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel
import com.stslex.csplashscreen.feature.topics.domain.model.toTopicsUI

class TopicsInteractorImpl(
    private val pagingSource: PagingSource<Int, TopicsModel>,
) : TopicsInteractor {

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        )
    }

    override val topics: Flow<PagingData<TopicsUIModel>>
        get() = Pager(pagingConfig) { pagingSource }.flow.map { pagingData ->
            pagingData.map { topics -> topics.toTopicsUI() }
        }

    companion object {
        private const val PAGE_SIZE = 10
    }
}