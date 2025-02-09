package st.slex.csplashscreen.feature.photo_detail.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import st.slex.csplashscreen.core.ui.theme.Dimen
import st.slex.csplashscreen.feature.photo_detail.ui.presenter.DownloadImageType
import st.slex.csplashscreen.feature.photo_detail.ui.presenter.ImageDetailStoreComponent

@Composable
fun DownloadImageDialog(
    onAction: (ImageDetailStoreComponent.Action.DownloadImageChooseClick) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(Dimen.medium))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(Dimen.large)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Download resolution"
            )
            Spacer(modifier = Modifier.height(Dimen.medium))
            DownloadImageType.entries.forEachIndexed { index, type ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(ImageDetailStoreComponent.Action.DownloadImageChooseClick(type))
                    },
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = type.name,
                        textAlign = TextAlign.Center
                    )
                }

                if (index != DownloadImageType.entries.size.dec()) {
                    Spacer(modifier = Modifier.height(Dimen.small))
                }

                if (type == DownloadImageType.ORIGINAL) {
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(Dimen.small))
                }
            }
        }
    }
}