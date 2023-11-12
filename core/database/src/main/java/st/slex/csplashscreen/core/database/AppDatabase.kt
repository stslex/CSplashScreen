package st.slex.csplashscreen.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.favourite.FavouriteEntity
import st.slex.csplashscreen.core.database.search.SearchDao
import st.slex.csplashscreen.core.database.search.SearchEntity

@Database(
    entities = [SearchEntity::class, FavouriteEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = AppDatabase.AutoMigrationFirstSpec::class
        )
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val favouriteDao: FavouriteDao

    abstract val searchDao: SearchDao

    @RenameColumn(
        tableName = "favourite_table",
        fromColumnName = "url",
        toColumnName = "url_raw",
    )
    class AutoMigrationFirstSpec : AutoMigrationSpec {

        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            db.query("SELECT * FROM favourite_table").use { cursor ->
                while (cursor.moveToNext()) {
                    val uuidIndex = cursor.getColumnIndex("uuid").coerceAtLeast(0)
                    val uuid = cursor.getString(uuidIndex)
                    val urlRawIndex = cursor.getColumnIndex("url_raw").coerceAtLeast(0)
                    val urlRaw = cursor.getString(urlRawIndex)
                    db.execSQL("UPDATE favourite_table SET url_full = '$urlRaw' WHERE uuid = '$uuid'")
                    db.execSQL("UPDATE favourite_table SET url_regular = '$urlRaw' WHERE uuid = '$uuid'")
                    db.execSQL("UPDATE favourite_table SET url_small = '$urlRaw' WHERE uuid = '$uuid'")
                    db.execSQL("UPDATE favourite_table SET url_thumb = '$urlRaw' WHERE uuid = '$uuid'")
                }
                cursor.close()
            }

            super.onPostMigrate(db)
        }
    }

    companion object {
        const val NAME = "app.db"
    }
}
