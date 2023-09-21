package st.slex.csplashscreen.core.database

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.favourite.FavouriteEntity
import java.util.UUID

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class FavouriteDaoTest {

    private val dao: FavouriteDao

    init {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .build()
        dao = database.favouriteDao
    }

    @Test
    fun `add single item to db`() = runBlocking(Dispatchers.IO) {
        val expected = generateRandomItem()
        dao.add(expected)
        val actual = dao.getItem(expected.uuid)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `remove item from db`() = runBlocking(Dispatchers.IO) {
        val expected = generateRandomItem()
        dao.add(expected)
        val actual = dao.getItem(expected.uuid)
        Assert.assertEquals(expected, actual)
        dao.remove(expected.uuid)
        val actualRemoved = dao.getItem(expected.uuid)
        Assert.assertNull(actualRemoved)
    }

    @Test
    fun `get flow items`() = runBlocking(Dispatchers.IO) {
        val expected = generateRandomItem()
        dao.add(expected)
        val actual = dao.getFlow(expected.uuid)
            .cancellable()
            .firstOrNull()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get paging items`() = runBlocking(Dispatchers.IO) {
        val insertItemSize = 10
        val pageLoadSize = 15
        repeat(insertItemSize) {
            val expected = generateRandomItem()
            dao.add(expected)
        }
        val loadResult = dao.getAll().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = pageLoadSize,
                placeholdersEnabled = false
            )
        )
        val actual = (loadResult as? PagingSource.LoadResult.Page)?.data
        Assert.assertNotNull(actual)
        Assert.assertTrue(actual!!.size in insertItemSize..pageLoadSize)
    }

    private fun generateRandomItem(): FavouriteEntity = UUID.randomUUID().toString()
        .let { uuid ->
            FavouriteEntity(
                uuid = uuid,
                url = "url_$uuid",
                username = "username_$uuid",
                userUrl = "user_url_$uuid",
                downloadUrl = "download_url_$uuid",
                tags = "tags_$uuid",
            )
        }

    companion object {
        private lateinit var database: AppDatabase

        @AfterClass
        @JvmStatic
        fun afterTestsEnd() {
            database.close()
        }
    }
}