package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import st.slex.core_navigation.AppDestinations
import st.slex.core_navigation.NavHostResource
import st.slex.core_navigation.NavHostResource.CollectionScreen
import st.slex.core_navigation.NavHostResource.ImageDetailScreen
import st.slex.core_navigation.NavHostResource.RawImageScreen
import st.slex.core_navigation.NavHostResource.SearchPhotosScreen
import st.slex.core_navigation.NavHostResource.UserScreen
import st.slex.csplashscreen.ui.screens.collection.CollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen
import st.slex.feature_main.navigation.homeGraph

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

    homeGraph(
        modifier = modifier,
        onProfileClick = { username ->
            navController.navigate("${UserScreen.destination}/$username")
        },
        onCollectionClick = { id ->
            navController.navigate("${CollectionScreen.destination}/$id")
        },
        onImageClick = { url, id ->
            navController.navigate("${ImageDetailScreen.destination}/$url/$id")
        }
    )

    create(ImageDetailScreen) { arguments ->
        ImageDetailScreen(
            onProfileClick = { username ->
                navController.navigate("${UserScreen.destination}/$username")
            },
            onTagClick = { tag ->
                navController.navigate("${SearchPhotosScreen.destination}/$tag")
            },
            onImageClick = { url ->
                navController.navigate("${RawImageScreen.destination}/$url")
            },
            arguments = arguments
        )
    }

    create(RawImageScreen) { RawImageScreen(navController, it) }
    create(SearchPhotosScreen) { arguments ->
        SearchPhotosScreen(
            onProfileClick = { username ->
                navController.navigate("${UserScreen.destination}/$username")
            },
            onImageClick = { url, id ->
                navController.navigate("${ImageDetailScreen.destination}/$url/$id")
            },
            arguments = arguments
        )
    }
    create(UserScreen) { UserScreen(navController, it) }
    create(NavHostResource.TopicsScreen) { TopicsScreen() }
    create(CollectionScreen) { arguments ->
        CollectionScreen(
            onProfileClick = { username ->
                navController.navigate("${UserScreen.destination}/$username")
            },
            onImageClick = { url, id ->
                navController.navigate("${ImageDetailScreen.destination}/$url/$id")
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