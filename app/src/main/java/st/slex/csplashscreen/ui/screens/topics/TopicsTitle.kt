package st.slex.csplashscreen.ui.screens.topics

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun TopicsTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onBackground
    )
}