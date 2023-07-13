package st.slex.feature_user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_network.model.ui.user.UserModel
import st.slex.feature_user.ui.components.BindUserBio
import st.slex.feature_user.ui.components.UserHeadComponent
import st.slex.feature_user.ui.components.UserTopAppbarComponent
import st.slex.feature_user.ui.components.pager.UserContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    photos: LazyPagingItems<PhotoModel>,
    likes: LazyPagingItems<PhotoModel>,
    collections: LazyPagingItems<CollectionModel>,
    popBackStack: () -> Unit,
    onUserClick: (username: String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    userFlow: () -> StateFlow<UserModel?>,
    username: String,
    modifier: Modifier = Modifier,
) {
    val user: UserModel? by remember {
        userFlow()
    }.collectAsState()

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            UserTopAppbarComponent(
                modifier = Modifier,
                scrollBehavior = scrollBehavior,
                title = username,
                popBackStack = popBackStack
            )
        },
        containerColor = Color.Transparent,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                user?.let { userModel ->
                    UserHeader(userModel)
                }
                UserContent(
                    photos = photos,
                    likes = likes,
                    collections = collections,
                    onImageClick = onImageClick,
                    onUserClick = onUserClick,
                    onCollectionClick = onCollectionClick
                )
            }
        }
    )
}

@Composable
fun UserHeader(
    user: UserModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface
        )
    ) {
        UserHeadComponent(
            totalPhotos = user.totalPhotos,
            totalLikes = user.totalLikes,
            totalCollections = user.totalCollections,
            url = user.profileImageModel.large
        )
        Spacer(modifier = Modifier.size(16.dp))
        Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        Spacer(modifier = Modifier.size(16.dp))
        if (user.bio.isNotEmpty()) {
            BindUserBio(bioText = user.bio)
            Spacer(modifier = Modifier.size(16.dp))
            Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        }
    }
}
