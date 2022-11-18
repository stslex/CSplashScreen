package st.slex.feature_topics.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.core_ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val pagingSource: PagingSource<Int, TopicsModel>,
) : BaseViewModel() {

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

    private val newPager by lazy {
        Pager(pagingConfig) { pagingSource }
    }

    val topics: StateFlow<PagingData<TopicsModel>> = newPager.flow.makeStateFlow(PagingData.empty())
}