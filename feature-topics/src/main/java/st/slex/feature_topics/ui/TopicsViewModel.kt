package st.slex.feature_topics.ui

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import st.slex.feature_topics.domain.TopicsInteractor
import st.slex.feature_topics.domain.model.TopicsUIModel

class TopicsViewModel(
    private val interactor: TopicsInteractor,
) : BaseViewModel() {

    val topics: StateFlow<PagingData<TopicsUIModel>>
        get() = interactor.topics.primaryPagingFlow
}