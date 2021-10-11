package st.slex.csplashscreen.ui.screens.topics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.titles.TopicsPagingSource
import javax.inject.Inject

class TopicsViewModel @Inject constructor(
    private val pagingSource: TopicsPagingSource
) : ViewModel() {

    private val newPager by lazy {
        Pager(PagingConfig(5, enablePlaceholders = false)) { pagingSource }
    }

    val topics: StateFlow<PagingData<TopicsModel>> = newPager.flow.cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )


}