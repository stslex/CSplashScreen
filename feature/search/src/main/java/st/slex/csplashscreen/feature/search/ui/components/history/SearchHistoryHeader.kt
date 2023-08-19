package st.slex.csplashscreen.feature.search.ui.components.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import st.slex.csplashscreen.core.ui.components.base.MediumText
import st.slex.csplashscreen.core.ui.components.base.SmallText

@Composable
fun SearchHistoryHeader(
    clearHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MediumText(
                modifier = Modifier.weight(1f),
                text = "Search history"
            )

            OutlinedButton(
                onClick = clearHistory
            ) {
                SmallText(text = "Clear history")
            }
        }
    }
}

@Composable
fun SearchHistoryDateTime(
    text: String,
    modifier: Modifier = Modifier
) {
    MediumText(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(
                    alpha = 0.7f
                )
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        text = text
    )
}