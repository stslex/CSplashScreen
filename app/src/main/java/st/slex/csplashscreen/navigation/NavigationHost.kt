package st.slex.csplashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import st.slex.core_navigation.testing.AppDestination
import st.slex.feature_collection.navigation.singleCollectionGraph
import st.slex.feature_image_raw.navigation.rawImageGraph
import st.slex.feature_main.navigation.homeGraph
import st.slex.feature_photo_detail.navigation.imageDetailGraph
import st.slex.feature_search_photos.navigation.searchPhotosGraph
import st.slex.feature_topics.navigation.topicsGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppDestination.HOME.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeGraph(modifier = modifier)
        imageDetailGraph(modifier = modifier)
        rawImageGraph(modifier = modifier)
        searchPhotosGraph(modifier = modifier)
        topicsGraph(modifier = modifier)
        singleCollectionGraph(modifier = modifier)
    }
}
