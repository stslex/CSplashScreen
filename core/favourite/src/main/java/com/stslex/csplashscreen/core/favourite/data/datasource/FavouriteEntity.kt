package com.stslex.csplashscreen.core.favourite.data.datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "user_url")
    val userUrl: String,
    @ColumnInfo(name = "download_url")
    val downloadUrl: String,
    @ColumnInfo(name = "tags")
    val tags: String,
)
