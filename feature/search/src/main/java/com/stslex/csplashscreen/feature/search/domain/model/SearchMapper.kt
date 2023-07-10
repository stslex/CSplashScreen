package com.stslex.csplashscreen.feature.search.domain.model

import com.stslex.csplashscreen.feature.search.data.database.SearchEntity
import com.stslex.csplashscreen.feature.search.ui.model.SearchElement
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

object SearchMapper {

    fun SearchEntity.toPresentation(): SearchElement.Item = SearchElement.Item(
        query = query,
        dateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
    )
}