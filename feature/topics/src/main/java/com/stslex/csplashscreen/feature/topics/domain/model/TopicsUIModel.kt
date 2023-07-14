package com.stslex.csplashscreen.feature.topics.domain.model

import androidx.compose.runtime.Stable

@Stable
data class TopicsUIModel(
    val id: String,
    val title: String,
    val url: String,
    val description: String
)
