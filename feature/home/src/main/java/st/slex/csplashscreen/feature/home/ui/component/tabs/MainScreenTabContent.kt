package st.slex.csplashscreen.feature.home.ui.component.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenTabContent(
    pagerState: PagerState,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    MainScreenTabs.values().forEachIndexed { index, tab ->
        Tab(
            modifier = modifier,
            text = {
                Text(
                    text = stringResource(id = tab.titleRes),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground
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