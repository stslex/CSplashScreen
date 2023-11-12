package st.slex.csplashscreen.core.database.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "url_raw", defaultValue = "")
    val urlRaw: String,
    @ColumnInfo(name = "url_full", defaultValue = "")
    val urlFull: String,
    @ColumnInfo(name = "url_regular", defaultValue = "")
    val urlRegular: String,
    @ColumnInfo(name = "url_small", defaultValue = "")
    val urlSmall: String,
    @ColumnInfo(name = "url_thumb", defaultValue = "")
    val urlThumb: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "user_url")
    val userUrl: String,
    @ColumnInfo(name = "download_url")
    val downloadUrl: String,
    @ColumnInfo(name = "tags")
    val tags: String,
)
