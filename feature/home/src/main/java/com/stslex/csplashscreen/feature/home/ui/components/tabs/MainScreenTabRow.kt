package com.stslex.csplashscreen.feature.home.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenTabRow(
    pagerState: PagerState,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { listTabPosition ->
            listTabPosition
                .getOrNull(pagerState.currentPage)
                ?.let { position ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(position)
                    )
                }
        },
        tabs = {
            MainScreenTabContent(
                pagerState = pagerState,
                onClick = onClick,
            )
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground
    )
}
