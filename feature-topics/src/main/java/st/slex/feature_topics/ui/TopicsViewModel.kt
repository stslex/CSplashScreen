package st.slex.feature_topics.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_navigation.NavigationScreen
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_topics.domain.TopicsInteractor
import st.slex.feature_topics.domain.model.TopicsUIModel

class TopicsViewModel(
    private val interactor: TopicsInteractor,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    val topics: StateFlow<PagingData<TopicsUIModel>>
        get() = interactor.topics.primaryPagingFlow
}