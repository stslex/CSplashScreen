package st.slex.feature_topics.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import st.slex.feature_topics.domain.model.TopicsUIModel

interface TopicsInteractor {
    val topics: Flow<PagingData<TopicsUIModel>>
}
