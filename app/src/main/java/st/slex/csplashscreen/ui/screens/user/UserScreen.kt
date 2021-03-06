package st.slex.csplashscreen.ui.screens.user

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.google.accompanist.pager.*
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.user.UserModel
import st.slex.csplashscreen.ui.components.CollectionItem
import st.slex.csplashscreen.ui.components.ImageItem
import st.slex.csplashscreen.ui.components.animatePager
import st.slex.csplashscreen.ui.components.checkState
import st.slex.csplashscreen.ui.screens.main.AnalyticsService
import st.slex.csplashscreen.ui.theme.Typography

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun UserScreen(
    navController: NavController,
    arguments: List<String>,
    viewModel: UserViewModel = hiltViewModel()
) {
    val username: String = arguments.first()

    viewModel.setAllQueries(username = username)

    val userResource: Resource<UserModel> by remember(viewModel) {
        viewModel.getUser(username = username)
    }.collectAsState(Resource.Loading)

    Scaffold(
        topBar = bindUserTopAppBar(username, navController),
        content = checkResultAndBind(
            userResource,
            navController
        ) { viewModel.getListOfPagesResource(it) }
    )
}

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
private fun checkResultAndBind(
    userResource: Resource<UserModel>,
    navController: NavController,
    getListOfPagesResource: @Composable (UserModel) -> List<UserPagerTabResource<out Parcelable>>,
): @Composable (PaddingValues) -> Unit = {
    when (userResource) {
        is Resource.Success -> {
            val listPagesResource = getListOfPagesResource(userResource.data)
            Column(modifier = Modifier.fillMaxSize()) {
                BindUserScreenMainHeader(user = userResource.data)
                BindPagerWithTabs(
                    listPagesResource = listPagesResource,
                    navController = navController
                )
            }
        }
        is Resource.Failure -> Unit
        is Resource.Loading -> Unit
    }
}

@Composable
@ExperimentalCoroutinesApi
private fun UserViewModel.getListOfPagesResource(
    user: UserModel
): List<UserPagerTabResource<out Parcelable>> = mapOf(
    UserPagerTabResource.Photos(photos.collectAsLazyPagingItems()) to user.total_photos,
    UserPagerTabResource.Likes(likes.collectAsLazyPagingItems()) to user.total_likes,
    UserPagerTabResource.Collections(collections.collectAsLazyPagingItems()) to user.total_collections
).filterEmptyItems()

private fun Map<UserPagerTabResource<out Parcelable>, Int>.filterEmptyItems() =
    this.filter { map -> map.value != 0 }.keys.toList()

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun BindUserScreenMainHeader(
    user: UserModel
) {
    BindUserHeader(
        total_photos = user.total_photos,
        total_likes = user.total_likes,
        total_collections = user.total_collections,
        url = user.profile_image.large
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

@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun BindPagerWithTabs(
    listPagesResource: List<UserPagerTabResource<out Parcelable>>,
    pagerState: PagerState = rememberPagerState(),
    navController: NavController
) {
    PagerLaunchedEffect(pagerState = pagerState)

    TabRow(pagerState = pagerState, listPagesResource = listPagesResource)

    HorizontalPager(
        count = listPagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()

        @Composable
        fun Parcelable.SetItemDependsOfType(id: String, isUserVisible: Boolean) {
            val animateModifier: Modifier =
                Modifier.animate(this@HorizontalPager, pageNumber, listState, id)
            SetCurrentItem(
                navController = navController,
                modifier = animateModifier,
                isUserVisible = isUserVisible
            )
        }

        LazyColumn(state = listState) {
            val pagingResource = listPagesResource[pageNumber]
            when (pagingResource) {
                is UserPagerTabResource.Photos -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id, isUserVisible = false)
                    }
                }
                is UserPagerTabResource.Likes -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id, isUserVisible = true)
                    }
                }
                is UserPagerTabResource.Collections -> {
                    items(pagingResource.pagingItems, key = { it.id }) { item ->
                        item?.SetItemDependsOfType(id = item.id, isUserVisible = false)
                    }
                }
            }
            pagingResource.pagingItems.checkState(this)
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun PagerLaunchedEffect(pagerState: PagerState) = LaunchedEffect(pagerState) {
    snapshotFlow { pagerState.currentPage }.collect { page ->
        AnalyticsService.sendPageSelectedEvent(page)
    }
}

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun Parcelable.SetCurrentItem(
    navController: NavController,
    modifier: Modifier,
    isUserVisible: Boolean
) {
    when (this) {
        is ImageModel -> ImageItem(
            item = this,
            modifier = modifier,
            navController = navController,
            isUserVisible = isUserVisible
        )
        is CollectionModel -> CollectionItem(
            item = this,
            modifier = modifier,
            navController = navController,
            isUserVisible = isUserVisible
        )
    }
}

@ExperimentalPagerApi
@Composable
private fun TabRow(
    pagerState: PagerState,
    listPagesResource: List<UserPagerTabResource<out Parcelable>>,
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
                            style = Typography.titleMedium
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

@ExperimentalPagerApi
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

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun BindUserBio(bio: String) {
    val maxLinesOfBio = remember { mutableStateOf(1) }
    Surface(
        elevation = 16.dp,
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            maxLinesOfBio.value = if (maxLinesOfBio.value == 1) Int.MAX_VALUE else 1
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bio",
                style = Typography.titleMedium
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnimatedContent(
                targetState = maxLinesOfBio.value
            ) { target ->
                Text(
                    text = bio,
                    maxLines = target,
                    style = Typography.titleMedium,
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
    navController: NavController
): @Composable () -> Unit = {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = "return"
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                style = Typography.titleMedium,
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
            style = Typography.titleMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contentTitle,
            style = Typography.titleMedium
        )
    }
}