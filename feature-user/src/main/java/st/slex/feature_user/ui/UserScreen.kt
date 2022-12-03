package st.slex.feature_user.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Tab
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_ui.CheckResults
import st.slex.core_ui.components.CollectionItem
import st.slex.core_ui.components.ImageItem
import st.slex.core_ui.components.animatePager
import st.slex.core_ui.components.checkState
import st.slex.core_ui.pagerTabIndicatorOffset
import st.slex.feature_user.ui.components.BindUserBio
import st.slex.feature_user.ui.components.UserContentSuccessComponent
import st.slex.feature_user.ui.components.UserHeadComponent
import st.slex.feature_user.ui.components.UserTopAppbarComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
) {
    val userResource: Resource<UserModel> by remember(viewModel) {
        viewModel.user
    }.collectAsState(Resource.Loading)

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(appBarState)

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            UserTopAppbarComponent(
                modifier = Modifier,
                scrollBehavior = scrollBehavior,
                title = viewModel.username,
                popBackStack = viewModel::popBackStack
            )
        },
        containerColor = Color.Transparent,
        content = { paddingValues ->
            userResource.CheckResults(
                onSuccess = { userModel: UserModel ->
                    UserContentSuccessComponent(
                        modifier = Modifier.padding(paddingValues),
                        userModel = userModel,
                        listPagesResource = viewModel.listOfPagesResource(userModel),
                        onImageClick = viewModel::onImageClick,
                        onUserClick = viewModel::onUserClick,
                        onCollectionClick = viewModel::onCollectionClick
                    )
                }
            )
        }
    )
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
    modifier: Modifier = Modifier,
    user: UserModel
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BindPagerWithTabs(
    listPagesResource: List<UserPagerTabResource<out UIItemTypes>>,
    pagerState: PagerState = rememberPagerState(),
    onUserClick: (String) -> Unit,
    onImageClick: (url: String, id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit
) {
    TabRow(
        pagerState = pagerState,
        listPagesResource = listPagesResource
    )
    HorizontalPager(
        count = listPagesResource.size,
        state = pagerState
    ) { pageNumber ->
        val listState = rememberLazyListState()

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