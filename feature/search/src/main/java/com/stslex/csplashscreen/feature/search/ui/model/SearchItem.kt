package com.stslex.csplashscreen.feature.search.ui.model

import java.time.OffsetDateTime

data class SearchItem(
    val query: String,
    val dateTime: OffsetDateTime
) {
    val textDateTime: String
        get() = with(dateTime) {
            "$dayOfMonth $month $year"
        }
}
