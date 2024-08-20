package st.slex.csplashscreen.feature.home

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.feature.home.ui.MainScreen

// todo initial test for HomeScreen
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainScreenTest() {
        val collectionsFlow = flowOf(PagingData.from(emptyList<CollectionModel>()))
        val photosFlow = flowOf(PagingData.from(emptyList<PhotoModel>()))
        composeTestRule.setContent {
            val collections = remember { collectionsFlow }.collectAsLazyPagingItems()
            val photos = remember { photosFlow }.collectAsLazyPagingItems()
            MainScreen(
                navToImage = {},
                navToProfile = {},
                navToCollection = {},
                collections = collections,
                photos = photos
            )
        }

    }
}