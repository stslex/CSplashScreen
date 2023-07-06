package st.slex.feature_main.ui.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.ui.components.animateItemTop

@Composable
fun MainScreenBaseLazyList(
    count: Int,
    modifier: Modifier = Modifier,
    key: ((index: Int) -> Any),
    contentType: (index: Int) -> Any,
    content: @Composable BoxScope.(index: Int) -> Unit
) {
    val listState: LazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 32.dp
        )
    ) {

        items(
            count = count,
            key = key,
            contentType = contentType,
        ) { index ->
            Box(
                modifier = Modifier
                    .animateItemTop(
                        listState = listState,
                        key = key(index)
                    )
            ) {
                content(index)
            }
        }
    }
}
