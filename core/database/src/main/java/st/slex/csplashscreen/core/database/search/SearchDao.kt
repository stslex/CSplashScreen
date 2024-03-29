package st.slex.csplashscreen.core.database.search

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {

    @Query("SELECT * FROM search_table ORDER BY timestamp DESC")
    fun getAllSearch(): PagingSource<Int, SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearch(searchEntity: SearchEntity)

    @Query("DELETE FROM search_table")
    suspend fun clear()
}