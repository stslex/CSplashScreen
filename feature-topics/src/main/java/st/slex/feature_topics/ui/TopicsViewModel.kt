package st.slex.feature_topics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import st.slex.core_network.model.ui.topics.TopicsModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val pagingSource: PagingSource<Int, TopicsModel>,
) : ViewModel() {

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

    private val newPager by lazy {
        Pager(pagingConfig) { pagingSource }
    }

    val topics: StateFlow<PagingData<TopicsModel>> = newPager.flow
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )
}