package st.slex.csplashscreen.ui.screens.topics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import javax.inject.Inject

class TopicsViewModel @Inject constructor(
    private val pagingSource: PagingSource<Int, TopicsModel>,
) : ViewModel() {

    private val newPager by lazy {
        Pager(PagingConfig(10, enablePlaceholders = false)) { pagingSource }
    }

    val topics: StateFlow<PagingData<TopicsModel>> = newPager.flow.cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )
}