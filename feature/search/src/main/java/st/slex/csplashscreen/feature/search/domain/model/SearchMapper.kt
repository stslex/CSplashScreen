package st.slex.csplashscreen.feature.search.domain.model

import st.slex.csplashscreen.feature.search.data.database.SearchEntity
import st.slex.csplashscreen.feature.search.ui.model.SearchItem
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

object SearchMapper {

    fun SearchEntity.toPresentation(): SearchItem = SearchItem(
        query = query,
        dateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
    )
}