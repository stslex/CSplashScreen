package com.stslex.csplashscreen.feature.user.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.stslex.csplashscreen.core.ui.base.DimensionSubcomposeLayout
import com.stslex.csplashscreen.core.ui.utils.UiExt.toPx
import com.stslex.csplashscreen.feature.user.ui.components.header.UserHeader
import com.stslex.csplashscreen.feature.user.ui.components.pager.UserPager
import com.stslex.csplashscreen.feature.user.ui.components.toolbar.UserToolbar
import com.stslex.csplashscreen.feature.user.ui.state.UserScreenState
import com.stslex.csplashscreen.feature.user.ui.state.UserSwipeState
import com.stslex.csplashscreen.feature.user.ui.utils.SwipeState

@Composable
fun UserScreen(
    userScreenState: UserScreenState,
    modifier: Modifier = Modifier,
) {
    DimensionSubcomposeLayout(
        mainContent = {
            UserHeader(userScreenState.user)
        }
    ) { contentSize ->

        val initialHeight = contentSize.height.toPx

        Column(
            modifier = modifier
                .userSwipeable(
                    state = userScreenState.userSwipeState,
                    initialHeight = initialHeight
                )
        ) {
            UserToolbar(
                username = userScreenState.username,
                popBackStack = userScreenState.navigation.popBackStack
            )

            UserHeader(
                modifier = Modifier
                    .height(userScreenState.userSwipeState.offsetDp),
                user = userScreenState.user
            )

            UserPager(
                userPagerState = userScreenState.userPagerState,
                navigation = userScreenState.navigation
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun Modifier.userSwipeable(
    state: UserSwipeState,
    initialHeight: Float
): Modifier = this
    .swipeable(
        state = state.swipeableState,
        anchors = mapOf(
            0f to SwipeState.COLLAPSE,
            initialHeight to SwipeState.EXPAND
        ),
        orientation = Orientation.Vertical,
    )
    .nestedScroll(
        connection = state.swipeScrollConnection
    )
