package st.slex.csplashscreen.feature.user.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import st.slex.csplashscreen.core.ui.utils.tabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserTabsRow(
    pagerState: PagerState,
    userTabs: Set<UserTab>,
    onClick: suspend (Int) -> Unit,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            tabPositions
                .getOrNull(pagerState.currentPage)
                ?.let { tabPosition ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(
                            currentTabPosition = tabPosition,
                        )
                    )
                }
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
                    onClick = remember {
                        {
                            scope.launch {
                                if (pagerState.currentPage == index) {
                                    onClick(index)
                                } else {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}
