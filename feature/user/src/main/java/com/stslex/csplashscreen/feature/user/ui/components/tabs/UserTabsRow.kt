package com.stslex.csplashscreen.feature.user.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.stslex.csplashscreen.core.ui.utils.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserTabsRow(
    pagerState: PagerState,
    userTabs: Set<UserTab>,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        tabs = {
            userTabs.forEachIndexed { index, model ->
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
                    },
                )
            }
        }
    )
}
