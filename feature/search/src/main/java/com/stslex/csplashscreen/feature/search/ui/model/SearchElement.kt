package com.stslex.csplashscreen.feature.search.ui.model

import java.time.OffsetDateTime

sealed interface SearchElement {

    data class Item(
        val query: String,
        val dateTime: OffsetDateTime
    ) : SearchElement

    data class Separator(
        val dateTime: OffsetDateTime
    ) : SearchElement {

        val textDateTime: String
            get() = "${dateTime.dayOfMonth} ${dateTime.month} ${dateTime.year}"
    }
}