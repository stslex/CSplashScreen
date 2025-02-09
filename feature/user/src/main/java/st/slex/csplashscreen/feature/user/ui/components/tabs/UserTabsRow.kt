package st.slex.csplashscreen.feature.user.ui.components.tabs

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
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.ui.utils.tabIndicatorOffset

@Composable
fun UserTabsRow(
    pagerState: PagerState,
    userTabs: ImmutableSet<UserTab>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            tabPositions
                .getOrNull(pagerState.currentPage)
                ?.let { tabPosition ->
                    TabRowDefaults.Indicator(Modifier.tabIndicatorOffset(currentTabPosition = tabPosition))
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
                            if (pagerState.currentPage == index) {
                                onClick(index)
                            } else {
                                scope.launch {
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
