package st.slex.csplashscreen.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.feature.collection.navigation.singleCollectionGraph
import st.slex.csplashscreen.feature.favourite.navigation.favouriteGraph
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.imageDetailGraph
import st.slex.csplashscreen.feature.home.navigation.homeGraph
import st.slex.csplashscreen.feature.search.navigation.searchPhotosGraph
import st.slex.csplashscreen.feature.user.navigation.userGraph

@Stable
class NavHostControllerHolder(val navController: NavHostController)

@Composable
@Stable
fun NavigationHost(
    holder: NavHostControllerHolder,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.HOME
) {
    NavHost(
        navController = holder.navController,
        startDestination = startDestination.navigationRoute
    ) {
        homeGraph(modifier)
        userGraph(modifier)
        imageDetailGraph(modifier)
        searchPhotosGraph(modifier)
        singleCollectionGraph(modifier)
        favouriteGraph(modifier)
    }
}
