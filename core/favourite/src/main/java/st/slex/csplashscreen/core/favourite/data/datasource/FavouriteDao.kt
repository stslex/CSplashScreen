package st.slex.csplashscreen.core.favourite.data.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite_table")
    fun getAll(): PagingSource<Int, FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: FavouriteEntity)

    @Query("DELETE FROM favourite_table WHERE uuid = :uuid")
    suspend fun remove(uuid: String)

    @Query("SELECT * FROM favourite_table WHERE uuid = :uuid")
    fun getFlow(uuid: String): Flow<FavouriteEntity?>

    @Query("SELECT * FROM favourite_table WHERE uuid = :uuid")
    fun getItem(uuid: String): FavouriteEntity?
}