package st.slex.feature_main.ui

import android.os.Parcelable
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import st.slex.core_ui.theme.Typography

@ExperimentalPagerApi
@Composable
fun TabRow(
    pagerState: PagerState,
    listPagesResource: List<MainPagerTabResource<out Parcelable>>
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = tabIndicator(pagerState = pagerState),
        tabs = tabsContent(listPagesResource, pagerState),
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}

@ExperimentalPagerApi
private fun tabIndicator(
    pagerState: PagerState
): @Composable (List<TabPosition>) -> Unit = { tabPositions ->
    TabRowDefaults.Indicator(
        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
    )
}

@Composable
@ExperimentalPagerApi
private fun tabsContent(
    listPagesResource: List<MainPagerTabResource<out Parcelable>>,
    pagerState: PagerState,
    scope: CoroutineScope = rememberCoroutineScope()
): @Composable () -> Unit = {
    listPagesResource.forEachIndexed { index, page ->
        Tab(
            text = page.tabTitle(),
            selected = pagerState.currentPage == index,
            onClick = {
                scope.launch { pagerState.animateScrollToPage(index) }
            }
        )
    }
}

@Composable
private fun MainPagerTabResource<out Parcelable>.tabTitle(): @Composable () -> Unit = {
    Text(text = title, style = Typography.labelMedium)
}