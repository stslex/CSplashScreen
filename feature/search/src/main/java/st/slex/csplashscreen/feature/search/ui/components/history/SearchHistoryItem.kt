package st.slex.csplashscreen.feature.search.ui.components.history

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import st.slex.csplashscreen.core.ui.components.animateItemTop
import st.slex.csplashscreen.core.ui.components.base.MediumText
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryItem(
    item: SearchItem,
    listState: LazyListState,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .animateItemTop(
                listState = listState,
                key = item.query,
                valueKf = 0.05f
            )
            .padding(16.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        onClick = remember(item) {
            { onSearchClick(item.query) }
        },
    ) {
        MediumText(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = item.query
        )
    }
}
