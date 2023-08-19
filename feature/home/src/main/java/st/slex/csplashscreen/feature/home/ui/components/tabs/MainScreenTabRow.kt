package st.slex.csplashscreen.feature.home.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import st.slex.csplashscreen.core.ui.utils.tabIndicatorOffset

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
                        modifier = Modifier.tabIndicatorOffset(
                            currentTabPosition = position,
                        )
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
