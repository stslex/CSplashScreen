package com.stslex.csplashscreen.feature.search.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchEntity::class],
    version = 0,
    exportSchema = false
)
abstract class SearchDatabase : RoomDatabase() {

    abstract val dao: SearchDao
}