package com.stslex.csplashscreen.feature.search.domain.model

import com.stslex.csplashscreen.feature.search.data.database.SearchEntity
import com.stslex.csplashscreen.feature.search.ui.model.SearchItem

object SearchMapper {

    fun SearchEntity.toPresentation(): SearchItem = SearchItem(
        query = query
    )
}