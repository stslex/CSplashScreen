package st.slex.feature_topics.domain.model

import st.slex.feature_topics.data.model.TopicsModel

fun TopicsModel.toTopicsUI() = TopicsUIModel(
    id = id,
    title = title,
    url = coverPhoto.urls.regular,
    description = description
)