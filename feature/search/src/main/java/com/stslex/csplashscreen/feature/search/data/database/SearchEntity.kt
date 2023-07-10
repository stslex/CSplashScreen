package com.stslex.csplashscreen.feature.search.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "query")
    val query: String
)