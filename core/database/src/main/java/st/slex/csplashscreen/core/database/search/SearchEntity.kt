package st.slex.csplashscreen.core.database.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "query")
    val query: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)