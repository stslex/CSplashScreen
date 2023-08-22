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
import st.slex.csplashscreen.feature.user.ui.state.UserScreenState
import st.slex.csplashscreen.feature.user.ui.utils.SwipeState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    userScreenState: UserScreenState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    DimensionSubcomposeLayout(
        mainContent = {
            UserHeader(
                user = userScreenState.user,
                onTabClick = {},
            )
        }
    ) { contentSize ->

        val initialHeight = contentSize.height.toPx

        Column(
            modifier = modifier
        ) {
            UserToolbar(
                username = userScreenState.username,
                popBackStack = userScreenState.navigation.popBackStack
            )

            Box(
                modifier = Modifier
                    .swipeable(
                        state = userScreenState.userSwipeState.swipeableState,
                        anchors = mapOf(
                            0f to SwipeState.COLLAPSE,
                            initialHeight to SwipeState.EXPAND
                        ),
                        orientation = Orientation.Vertical,
                    )
                    .nestedScroll(userScreenState.userSwipeState.swipeScrollConnection)
            ) {
                UserHeader(
                    modifier = Modifier,
                    user = userScreenState.user,
                    onTabClick = { tab ->
                        coroutineScope.launch {
                            userScreenState.userPagerState.navToPage(tab)
                        }
                    },
                )

                UserPager(
                    modifier = Modifier
                        .offset(
                            y = userScreenState.userSwipeState.offsetDp
                        )
                        .background(MaterialTheme.colorScheme.background),
                    userPagerState = userScreenState.userPagerState,
                    navigation = userScreenState.navigation
                )
            }
        }
    }
}
