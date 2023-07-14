package com.stslex.csplashscreen.feature.topics.ui

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractor
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel

class TopicsViewModel(
    private val interactor: TopicsInteractor,
) : BaseViewModel() {

    val topics: StateFlow<PagingData<TopicsUIModel>>
        get() = interactor.topics.primaryPagingFlow
}