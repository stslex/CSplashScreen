package st.slex.feature_main.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.feature_main.ui.MainPagerTabResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenTabContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    listPagesResource: List<MainPagerTabResource<out UIItemTypes>>,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    listPagesResource.forEachIndexed { index, page ->
        Tab(
            modifier = modifier,
            text = {
                Text(
                    text = stringResource(id = page.title),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            selected = pagerState.currentPage == index,
            onClick = {
                scope.launch { pagerState.animateScrollToPage(index) }
            }
        )
    }
}