package st.slex.csplashscreen.ui.screens.topics

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.ui.components.ImageComponent

fun topicsColumnContent(item: TopicsModel?): @Composable ColumnScope.() -> Unit = {
    TopicsTitle(title = item?.title.toString())
    ImageComponent(
        item?.cover_photo?.urls?.regular.toString(),
        Modifier
            .aspectRatio(1f)
            .padding(top = 32.dp)
    )
}