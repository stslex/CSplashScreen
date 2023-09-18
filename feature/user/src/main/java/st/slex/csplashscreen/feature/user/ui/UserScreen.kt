package st.slex.csplashscreen.feature.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.ui.base.DimensionSubcomposeLayout
import st.slex.csplashscreen.core.ui.utils.UiExt.toPx
import st.slex.csplashscreen.feature.user.ui.components.header.UserHeader
import st.slex.csplashscreen.feature.user.ui.components.pager.UserPager
import st.slex.csplashscreen.feature.user.ui.components.toolbar.UserToolbar
import st.slex.csplashscreen.feature.user.ui.state.UserPagerState
import st.slex.csplashscreen.feature.user.ui.state.UserSwipeState
import st.slex.csplashscreen.feature.user.ui.store.UserStore
import st.slex.csplashscreen.feature.user.ui.utils.SwipeState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    state: UserStore.State,
    userPagerState: UserPagerState,
    userSwipeState: UserSwipeState,
    popBackStack: () -> Unit,
    onUserClick: (username: String) -> Unit,
    onImageClick: (id: String) -> Unit,
    onCollectionClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    DimensionSubcomposeLayout(
        mainContent = {
            UserHeader(
                user = state.user,
                onTabClick = {},
            )
        }
    ) { contentSize ->
        val initialHeight = contentSize.height.toPx

        Column(
            modifier = modifier
        ) {
            UserToolbar(
                username = state.user?.username.orEmpty(),
                popBackStack = popBackStack
            )

            Box(
                modifier = Modifier
                    .swipeable(
                        state = userSwipeState.swipeableState,
                        anchors = mapOf(
                            0f to SwipeState.COLLAPSE,
                            initialHeight to SwipeState.EXPAND
                        ),
                        orientation = Orientation.Vertical,
                    )
                    .nestedScroll(userSwipeState.swipeScrollConnection)
            ) {
                UserHeader(
                    modifier = Modifier,
                    user = state.user,
                    onTabClick = { tab ->
                        coroutineScope.launch {
                            userPagerState.navToPage(tab)
                        }
                    },
                )

                UserPager(
                    modifier = Modifier
                        .offset(
                            y = userSwipeState.offsetDp
                        )
                        .background(MaterialTheme.colorScheme.background),
                    userPagerState = userPagerState,
                    onUserClick = onUserClick,
                    onImageClick = onImageClick,
                    onCollectionClick = onCollectionClick
                )
            }
        }
    }
}
