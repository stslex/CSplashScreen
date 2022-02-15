package st.slex.csplashscreen.ui.screens.topics

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import st.slex.csplashscreen.ui.theme.Typography

@Composable
internal fun TopicsTitle(title: String) {
    Text(
        text = title,
        style = Typography.titleLarge,
        maxLines = 1
    )
}