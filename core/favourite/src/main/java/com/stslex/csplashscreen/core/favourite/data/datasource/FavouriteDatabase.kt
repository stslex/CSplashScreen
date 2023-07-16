package com.stslex.csplashscreen.core.favourite.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract val dao: FavouriteDao

    companion object {
        const val NAME = "db.favourite"
    }
}