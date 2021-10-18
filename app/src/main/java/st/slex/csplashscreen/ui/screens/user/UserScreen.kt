package st.slex.csplashscreen.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import com.google.android.material.animation.AnimationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.core.QueryCollections
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.user.UserModel
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.*
import st.slex.csplashscreen.ui.screens.main.AnalyticsService
import st.slex.csplashscreen.ui.theme.Typography
import kotlin.math.absoluteValue

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun UserScreen(
    navController: NavController,
    username: String,
    viewModel: UserViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    viewModel.apply {
        setQueryCollections(QueryCollections.UserCollections(username))
        setQueryPhotos(QueryPhotos.UserPhotos(username))
        setQueryLikes(QueryPhotos.UserLikes(username))
    }

    val lazyPagingPhotos = viewModel.photos.collectAsLazyPagingItems()
    val lazyPagingCollections = viewModel.collections.collectAsLazyPagingItems()
    val lazyPagingLikes = viewModel.likes.collectAsLazyPagingItems()

    val result: Resource<UserModel> by remember(viewModel) {
        viewModel.getUser(username = username)
    }.collectAsState(Resource.Loading)

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            BindUserTopAppBar(
                username = username,
                navController = navController,
                listState = listState
            )
        }
    ) {
        when (result) {
            is Resource.Success -> {
                val user = (result as Resource.Success<UserModel>).data
                val pages = mapOf(
                    PagerUserTab.Photos to user.total_photos,
                    PagerUserTab.Likes to user.total_likes,
                    PagerUserTab.Collections to user.total_collections
                ).filter { map -> map.value != 0 }.keys.toList()
                Column(modifier = Modifier.fillMaxSize()) {
                    BindUserScreenMainHeader(
                        user = user
                    )
                    BindUserMainBody(
                        pages = pages,
                        lazyPagingPhotos = lazyPagingPhotos,
                        lazyPagingLikes = lazyPagingLikes,
                        lazyPagingCollections = lazyPagingCollections,
                        navController = navController
                    )
                }
            }
            is Resource.Failure -> {

            }
            is Resource.Loading -> {

            }
        }
    }

}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun BindUserScreenMainHeader(
    user: UserModel
) {
    BindUserHeader(
        total_photos = user.total_photos.toString(),
        total_likes = user.total_likes.toString(),
        total_collections = user.total_collections.toString(),
        url = user.profile_image?.large.toString()
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

sealed interface PagerUserTab {
    val title: String

    object Photos : PagerUserTab {
        override val title: String = "Photos"
    }

    object Likes : PagerUserTab {
        override val title: String = "Likes"
    }

    object Collections : PagerUserTab {
        override val title: String = "Collections"
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun BindUserMainBody(
    pages: List<PagerUserTab>,
    lazyPagingPhotos: LazyPagingItems<ImageModel>,
    lazyPagingLikes: LazyPagingItems<ImageModel>,
    lazyPagingCollections: LazyPagingItems<CollectionModel>,
    navController: NavController,
    pagerState: PagerState = rememberPagerState(),
) {
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            AnalyticsService.sendPageSelectedEvent(page)
        }
    }
    TabRow(pagerState = pagerState, pages = pages)
    Pages(
        lazyPagingPhotos = lazyPagingPhotos,
        lazyPagingCollections = lazyPagingCollections,
        lazyPagingLikes = lazyPagingLikes,
        navController = navController,
        pagerState = pagerState,
        pages = pages,
    )

}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun Pages(
    lazyPagingPhotos: LazyPagingItems<ImageModel>,
    lazyPagingCollections: LazyPagingItems<CollectionModel>,
    lazyPagingLikes: LazyPagingItems<ImageModel>,
    navController: NavController,
    pagerState: PagerState,
    pages: List<PagerUserTab>,
) {
    HorizontalPager(
        count = pages.size,
        state = pagerState,
    ) { page ->
        val lazyListState = rememberLazyListState()

        LazyColumn(
            state = lazyListState
        ) {
            when (pages[page]) {
                is PagerUserTab.Photos -> {
                    items(lazyPagingPhotos, key = { it.id }) { item ->
                        ImageItem(
                            item = item,
                            modifier = Modifier.animate(
                                scope = this@HorizontalPager,
                                page = page,
                                lazyListState = lazyListState,
                                id = item?.id.toString()
                            ),
                            navController = navController,
                            isUserVisible = false
                        )
                    }
                    lazyPagingPhotos.checkState(this)
                }

                is PagerUserTab.Likes -> {
                    items(lazyPagingLikes, key = { it.id }) { item ->
                        ImageItem(
                            item = item,
                            modifier = Modifier.animate(
                                scope = this@HorizontalPager,
                                page = page,
                                lazyListState = lazyListState,
                                id = item?.id.toString()
                            ),
                            navController = navController
                        )
                    }
                    lazyPagingLikes.checkState(this)
                }

                is PagerUserTab.Collections -> {
                    items(lazyPagingCollections, key = { it.id }) { item ->
                        CollectionItem(
                            item = item,
                            modifier = Modifier.animate(
                                scope = this@HorizontalPager,
                                page = page,
                                lazyListState = lazyListState,
                                id = item?.id.toString()
                            ),
                            navController = navController,
                            isUserVisible = false
                        )
                    }
                    lazyPagingCollections.checkState(this)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun TabRow(
    pagerState: PagerState,
    pages: List<PagerUserTab>,
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
            pages.forEachIndexed { index, model ->
                Tab(
                    text = {
                        Text(
                            text = model.title,
                            style = Typography.subtitle1
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
    .graphicsLayer {
        val pageOffset = scope.calculateCurrentOffsetForPage(page).absoluteValue
        AnimationUtils
            .lerp(
                0.85f,
                1f,
                1f - pageOffset.coerceIn(0f, 1f)
            )
            .also { scale ->
                scaleX = scale
                scaleY = scale
            }
        translationY = lazyListState.layoutInfo.normalizedItemPosition(id) * -50
        alpha = AnimationUtils.lerp(
            0.5f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
        )
    }
    .graphicsLayer {
        val value =
            1 - (lazyListState.layoutInfo.normalizedItemPosition(id).absoluteValue * 0.1f)
        alpha = value
        scaleX = value
        scaleY = value
    }

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
                style = Typography.subtitle1
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnimatedContent(
                targetState = maxLinesOfBio.value
            ) { target ->
                Text(
                    text = bio,
                    maxLines = target,
                    style = Typography.body1,
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun BindUserHeader(
    total_photos: String,
    total_likes: String,
    total_collections: String,
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
        Image(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .clip(CircleShape)
                .width(64.dp),
            painter = rememberImagePainter(
                data = url,
                builder = {
                    allowHardware(false)
                }
            ),
            contentDescription = "User Profile Image",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextHeaderColumn("Photos", total_photos)
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Likes", total_likes)
            Spacer(modifier = Modifier.size(16.dp))
            TextHeaderColumn("Collections", total_collections)
        }
    }
}

@Composable
fun BindUserTopAppBar(
    username: String,
    navController: NavController,
    listState: LazyListState,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(
                kotlin.math.max(
                    0.3f,
                    listState.firstVisibleItemIndex / listState.firstVisibleItemScrollOffset.toFloat()
                )
            )
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
                style = Typography.subtitle1,
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
            style = Typography.subtitle1
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contentTitle,
            style = Typography.subtitle2
        )
    }
}