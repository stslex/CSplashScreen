package com.stslex.csplashscreen.feature.user.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.collection.ui.model.CollectionModel
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.base.DimensionSubcomposeLayout
import com.stslex.csplashscreen.core.ui.utils.UiExt.toDp
import com.stslex.csplashscreen.core.ui.utils.UiExt.toPx
import com.stslex.csplashscreen.feature.user.ui.components.BindUserBio
import com.stslex.csplashscreen.feature.user.ui.components.UserHeadComponent
import com.stslex.csplashscreen.feature.user.ui.components.pager.UserContent
import com.stslex.csplashscreen.feature.user.ui.utils.SwipeScrollConnection
import com.stslex.csplashscreen.feature.user.ui.utils.SwipeState
import kotlinx.coroutines.flow.StateFlow
import st.slex.core_network.model.ui.user.UserModel

@OptIn(ExperimentalMaterialApi::class)
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

    DimensionSubcomposeLayout(
        mainContent = {
            UserHeader(user)
        }
    ) { contentSize ->

        val lazyListState = rememberLazyListState()
        val swipeableState = rememberSwipeableState(initialValue = SwipeState.EXPAND)

        val initialHeight = contentSize.height.toPx

        Column(
            modifier = modifier
                .swipeable(
                    state = swipeableState,
                    anchors = mapOf(
                        0f to SwipeState.COLLAPSE,
                        initialHeight to SwipeState.EXPAND
                    ),
                    orientation = Orientation.Vertical,
                )
                .nestedScroll(
                    connection = remember {
                        SwipeScrollConnection(
                            swipeableState = swipeableState,
                            lazyListState = lazyListState
                        )
                    }
                )
        ) {
            UsernameToolbar(
                username = username,
                popBackStack = popBackStack
            )

            UserHeader(
                modifier = Modifier
                    .height(swipeableState.offset.value.toDp),
                user = user
            )

            UserContent(
                photos = photos,
                likes = likes,
                collections = collections,
                onImageClick = onImageClick,
                onUserClick = onUserClick,
                lazyListState = lazyListState,
                onCollectionClick = onCollectionClick
            )
        }
    }
}

@Composable
fun UserHeader(
    user: UserModel?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        UserHeadComponent(
            totalPhotos = user?.totalPhotos ?: 0,
            totalLikes = user?.totalLikes ?: 0,
            totalCollections = user?.totalCollections ?: 0,
            url = user?.profileImageModel?.large.orEmpty()
        )
        Spacer(modifier = Modifier.size(16.dp))
        Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        Spacer(modifier = Modifier.size(16.dp))
        val bio = user?.bio ?: "bio"
        if (bio.isNotEmpty()) {
            BindUserBio(bioText = bio)
            Spacer(modifier = Modifier.size(16.dp))
            Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        }
    }
}

@Composable
fun UsernameToolbar(
    username: String,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp),
            onClick = popBackStack
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = "return"
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.displaySmall,
            text = username,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
    }
}
