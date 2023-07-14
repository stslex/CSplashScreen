package com.stslex.csplashscreen.feature.topics.domain.model

import com.stslex.csplashscreen.feature.topics.data.model.TopicsModel

fun TopicsModel.toTopicsUI() = TopicsUIModel(
    id = id,
    title = title,
    url = coverPhoto.urls.regular,
    description = description
)