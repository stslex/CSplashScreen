package st.slex.csplashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import st.slex.core_navigation.AppDestinations
import st.slex.core_navigation.NavHostResource
import st.slex.feature_collection.ui.CollectionScreen
import st.slex.feature_image_raw.RawImageScreen
import st.slex.feature_main.navigation.homeGraph
import st.slex.feature_photo_detail.ui.ImageDetailScreen
import st.slex.feature_search_photos.ui.SearchPhotosScreen
import st.slex.feature_topics.ui.TopicsScreen
import st.slex.feature_user.ui.UserScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppDestinations.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = builder(navController, modifier)
    )
}


private fun builder(
    navController: NavHostController,
    modifier: Modifier = Modifier
): NavGraphBuilder.() -> Unit = {

    homeGraph(modifier = modifier)

    create(NavHostResource.ImageDetailScreen) { arguments ->
        ImageDetailScreen(
            onProfileClick = { username ->
                navController.navigate("${NavHostResource.UserScreen.destination}/$username")
            },
            onTagClick = { tag ->
                navController.navigate("${NavHostResource.SearchPhotosScreen.destination}/$tag")
            },
            onImageClick = { url ->
                navController.navigate("${NavHostResource.RawImageScreen.destination}/$url")
            },
            arguments = arguments
        )
    }

    create(NavHostResource.RawImageScreen) {
        RawImageScreen(
            navController,
            it
        )
    }
    create(NavHostResource.SearchPhotosScreen) { arguments ->
        SearchPhotosScreen(
            onProfileClick = { username ->
                navController.navigate("${NavHostResource.UserScreen.destination}/$username")
            },
            onImageClick = { url, id ->
                navController.navigate("${NavHostResource.ImageDetailScreen.destination}/$url/$id")
            },
            arguments = arguments
        )
    }
    create(NavHostResource.UserScreen) { UserScreen(navController, it) }
    create(NavHostResource.TopicsScreen) { TopicsScreen() }
    create(NavHostResource.CollectionScreen) { arguments ->
        CollectionScreen(
            onProfileClick = { username ->
                navController.navigate("${NavHostResource.UserScreen.destination}/$username")
            },
            onImageClick = { url, id ->
                navController.navigate("${NavHostResource.ImageDetailScreen.destination}/$url/$id")
            },
            arguments = arguments
        )
    }
}

private inline fun NavGraphBuilder.create(
    navDest: NavHostResource,
    crossinline screen: @Composable (list: List<String>) -> Unit
) = with(navDest) {
    composable(route = convertRoute) { screen(it.convertArgs) }
}