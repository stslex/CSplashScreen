package st.slex.feature_main.ui.components.tabs

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.feature_main.ui.MainPagerTabResource

@ExperimentalPagerApi
@Composable
fun MainScreenTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    listPagesResource: List<MainPagerTabResource<out UIItemTypes>>
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { listTabPosition ->
            val currentTab = listTabPosition[pagerState.currentPage]
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(currentTab)
            )
        },
        tabs = {
            MainScreenTabContent(
                modifier = Modifier,
                pagerState = pagerState,
                listPagesResource = listPagesResource
            )
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground
    )
}
