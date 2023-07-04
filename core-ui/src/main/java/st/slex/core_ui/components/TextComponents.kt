package st.slex.core_ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextUsernamePrimary(
    modifier: Modifier = Modifier,
    username: String
) {
    Text(
        modifier = modifier,
        text = username,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        lineHeight = TextUnit.Unspecified
    )
}
