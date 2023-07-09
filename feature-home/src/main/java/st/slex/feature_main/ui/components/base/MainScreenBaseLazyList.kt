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
import com.stslex.csplashscreen.core.ui.components.animateItemTop
import com.stslex.csplashscreen.core.ui.theme.Dimen

@Composable
fun MainScreenBaseLazyList(
    count: Int,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    key: ((index: Int) -> Any),
    contentType: (index: Int) -> Any,
    content: @Composable BoxScope.(index: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            horizontal = Dimen.medium,
            vertical = Dimen.large
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
