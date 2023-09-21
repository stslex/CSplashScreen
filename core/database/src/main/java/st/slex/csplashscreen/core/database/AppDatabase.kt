package st.slex.csplashscreen.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.favourite.FavouriteEntity
import st.slex.csplashscreen.core.database.search.SearchDao
import st.slex.csplashscreen.core.database.search.SearchEntity

@Database(
    entities = [SearchEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val favouriteDao: FavouriteDao

    abstract val searchDao: SearchDao

    companion object {
        val NAME = "app.db"
    }
}

