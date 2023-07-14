package com.stslex.csplashscreen.feature.topics.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.core.ui.paging.PagingSource
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractor
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel
import kotlinx.coroutines.flow.StateFlow

class TopicsViewModel(
    private val interactor: TopicsInteractor,
) : BaseViewModel() {

    val topics: StateFlow<PagingData<TopicsUIModel>>
        get() = Pager(pagingConfig) {
            PagingSource(interactor::getTopics)
        }.pagingFlow

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        )
    }
}