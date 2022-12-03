package st.slex.feature_user.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_ui.components.CollectionItem
import st.slex.core_ui.components.ImageItem
import st.slex.core_ui.components.animatePager
import st.slex.core_ui.components.checkState


@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel
) {
    val userResource: Resource<UserModel> by remember(viewModel) {
        viewModel.user
    }.collectAsState(Resource.Loading)

    Scaffold(
        modifier = modifier,
        topBar = bindUserTopAppBar(
            username = viewModel.username,
            popBackStack = viewModel::popBackStack
        ),
        content = checkResultAndBind(
            userResource = userResource,
            getListOfPagesResource = viewModel.listOfPagesResource,
            onUserClick = viewModel::onUserClick,
            onCollectionClick = viewModel::onCollectionClick,
            onImageClick = viewModel::onImageClick,
        )
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun checkResultAndBind(
    userResource: Resource<UserModel>,
    getListOfPagesResource: @Composable (UserModel) -> List<UserPagerTabResource<out UIItemTypes>>,
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
): @Composable (PaddingValues) -> Unit = {
    when (userResource) {
        is Resource.Success -> {
            val listPagesResource = getListOfPagesResource(userResource.data)
            Column(modifier = Modifier.fillMaxSize()) {
                BindUserScreenMainHeader(user = userResource.data)
                BindPagerWithTabs(
                    listPagesResource = listPagesResource,
                    onImageClick = onImageClick,
                    onUserClick = onUserClick,
                    onCollectionClick = onCollectionClick
                )
            }
        }

        is Resource.Failure -> Unit
        is Resource.Loading -> Unit
    }
}

private val UserViewModel.listOfPagesResource: @Composable (UserModel) -> List<UserPagerTabResource<out UIItemTypes>>
    get() = { user ->
        mapOf(
            UserPagerTabResource.Photos(photos.collectAsLazyPagingItems()) to user.totalPhotos,
            UserPagerTabResource.Likes(likes.collectAsLazyPagingItems()) to user.totalLikes,
            UserPagerTabResource.Collections(collections.collectAsLazyPagingItems()) to user.totalCollections
        ).filterValues { value -> value != 0 }.keys.toList()
    }

@Composable
fun BindUserScreenMainHeader(
    user: UserModel
) {
    BindUserHeader(
        total_photos = user.totalPhotos,
        total_likes = user.totalLikes,
        total_collections = user.totalCollections,
        url = user.profileImageModel.large
    )
    Spacer(modifier = Modifier.size(16.dp))
    Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
    Spacer(modifier = Modifier.size(16.dp))
    if (user.bio.isNotEmpty()) {
        BindUserBio(bio = user.bio)
        Spacer(modifier = Modifier.size(16.dp))
        Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun BindPagerWithTabs(
    listPagesResource: List<UserPagerTabResource<out UIItemTypes>>,
    pagerState: PagerState = rememberPagerState(),
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
) {
    TabRow(
        pagerState = pagerState,
        listPagesResource = listPagesResource
    )
    HorizontalPager(
        count = listPagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState: LazyListState = rememberLazyListState()

        LazyColumn(state = listState) {
            val pagingResource = listPagesResource[pageNumber]
            items(
                items = pagingResource.pagingItems,
                key = { it.itemId }
            ) { lazyItem ->
                val item = lazyItem ?: return@items
                val animateModifier: Modifier =
                    Modifier.animate(
                        scope = this@HorizontalPager,
                        page = pageNumber,
                        lazyListState = listState,
                        id = item.itemId
                    )
                SetCurrentItem(
                    modifier = animateModifier,
                    item = item,
                    isUserVisible = pagingResource is UserPagerTabResource.Likes,
                    onUserClick = onUserClick,
                    onImageClick = onImageClick,
                    onCollectionClick = onCollectionClick
                )
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@Composable
private fun SetCurrentItem(
    modifier: Modifier = Modifier,
    item: UIItemTypes,
    isUserVisible: Boolean = true,
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    when (item) {
        is ImageModel -> ImageItem(
            item = item,
            modifier = modifier,
            isUserVisible = isUserVisible,
            onProfileClick = onUserClick,
            onImageClick = onImageClick
        )

        is CollectionModel -> CollectionItem(
            item = item,
            modifier = modifier,
            isUserVisible = isUserVisible,
            onUserHeadClick = onUserClick,
            onCollectionClick = onCollectionClick
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRow(
    pagerState: PagerState,
    listPagesResource: List<UserPagerTabResource<out UIItemTypes>>,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        tabs = {
            listPagesResource.forEachIndexed { index, model ->
                Tab(
                    text = {
                        Text(
                            text = model.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("RestrictedApi")
private fun Modifier.animate(
    scope: PagerScope,
    page: Int,
    lazyListState: LazyListState,
    id: String
): Modifier = this
    .size(250.dp, 250.dp)
    .padding(top = 32.dp, bottom = 32.dp)
    .animatePager(scope, page, lazyListState, id)


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BindUserBio(bio: String) {
    val maxLinesOfBio = remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 16.dp)
            .clickable {
                maxLinesOfBio.value = if (maxLinesOfBio.value == 1) Int.MAX_VALUE else 1
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bio",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnimatedContent(
                targetState = maxLinesOfBio.value
            ) { target ->
                Text(
                    text = bio,
                    maxLines = target,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Composable
fun BindUserHeader(
    total_photos: Int,
    total_likes: Int,
    total_collections: Int,
    url: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        GlideImage(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .clip(CircleShape)
                .size(64.dp),
            imageModel = url,
            contentScale = ContentScale.FillBounds,
            circularReveal = CircularReveal(duration = 1000),
            requestBuilder = {
                Glide.with(LocalContext.current.applicationContext).asDrawable()
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextHeaderColumn("Photos", total_photos.toString())
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Likes", total_likes.toString())
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Collections", total_collections.toString())
        }
    }
}

@Composable
fun bindUserTopAppBar(
    username: String,
    popBackStack: () -> Unit
): @Composable () -> Unit = {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = popBackStack) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = "return"
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = username,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
    }
}

@Composable
fun TextHeaderColumn(title: String, contentTitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contentTitle,
            style = MaterialTheme.typography.titleMedium
        )
    }
}