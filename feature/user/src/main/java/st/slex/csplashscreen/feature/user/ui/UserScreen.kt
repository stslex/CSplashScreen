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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import st.slex.csplashscreen.core.ui.base.DimensionSubcomposeLayout
import st.slex.csplashscreen.core.ui.utils.UiExt.toPx
import st.slex.csplashscreen.feature.user.ui.components.header.UserHeader
import st.slex.csplashscreen.feature.user.ui.components.pager.UserPager
import st.slex.csplashscreen.feature.user.ui.components.toolbar.UserToolbar
import st.slex.csplashscreen.feature.user.ui.state.UserScreenState
import st.slex.csplashscreen.feature.user.ui.utils.SwipeState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    userScreenState: UserScreenState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val itemsCount = 20
    DimensionSubcomposeLayout(
        mainContent = {
            UserHeader(
                user = userScreenState.user,
                onTabClick = {},
                itemsCount = itemsCount,
                lazyListState = userScreenState.headerLazyListState
            )
        }
    ) { contentSize ->

        val initialHeight = contentSize.height.toPx
        val isEnabled by remember {
            derivedStateOf {
                userScreenState.headerLazyListState.layoutInfo
                    .visibleItemsInfo
                    .lastOrNull()
                    ?.key == itemsCount.dec()
            }
        }

        val scrollConnectionModifier = remember(isEnabled) {
            if (isEnabled) {
                Modifier.nestedScroll(
                    connection = userScreenState.userSwipeState.swipeScrollConnection
                )
            } else {
                Modifier
            }
        }

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
                    .then(scrollConnectionModifier)
            ) {
                UserHeader(
                    modifier = Modifier,
                    user = userScreenState.user,
                    lazyListState = userScreenState.headerLazyListState,
                    onTabClick = { tab ->
                        coroutineScope.launch {
                            userScreenState.userPagerState.navToPage(tab)
                        }
                    },
                    itemsCount = itemsCount,
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
