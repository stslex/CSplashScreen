package com.stslex.csplashscreen.feature.search.ui.model

import java.time.OffsetDateTime

sealed class SearchElement(
    val date: OffsetDateTime
) {

    data class Item(
        val query: String,
        val dateTime: OffsetDateTime
    ) : SearchElement(dateTime)

    data class Separator(
        val dateTime: OffsetDateTime
    ) : SearchElement(dateTime)

    val textDateTime: String
        get() = "${date.dayOfMonth} ${date.month} ${date.year}"
}