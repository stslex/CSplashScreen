package st.slex.csplashscreen.core.database

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import st.slex.csplashscreen.core.database.search.SearchDao
import st.slex.csplashscreen.core.database.search.SearchEntity
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SearchDaoTest {

    private val dao: SearchDao

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .build()
        dao = database.getSearchDao()
    }

    @Test
    fun `add single item`() = runBlocking(Dispatchers.IO) {
        val expected = generateUniqueItem()
        dao.clear()
        dao.addSearch(expected)
        val loadResult = dao.getAllSearch().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )
        val actual = (loadResult as? PagingSource.LoadResult.Page)?.data?.firstOrNull()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `remove all items`() = runBlocking(Dispatchers.IO) {
        repeat(10) {
            val expected = generateUniqueItem()
            dao.addSearch(expected)
        }
        val loadPreResult = dao.getAllSearch()
            .load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 10,
                    placeholdersEnabled = false
                )
            )
            .let {
                (it as? PagingSource.LoadResult.Page)?.data.orEmpty()
            }
        Assert.assertTrue(loadPreResult.isNotEmpty())

        dao.clear()

        val loadResult = dao.getAllSearch()
            .load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 10,
                    placeholdersEnabled = false
                )
            )
            .let {
                (it as? PagingSource.LoadResult.Page)?.data
            }

        Assert.assertTrue(loadResult?.isEmpty() ?: false)
    }

    private fun generateUniqueItem() = SearchEntity(
        query = UUID.randomUUID().toString(),
        timestamp = System.currentTimeMillis()
    )

    companion object {
        private lateinit var database: AppDatabase

        @AfterClass
        @JvmStatic
        fun afterTestsEnd() {
            database.close()
        }
    }
}