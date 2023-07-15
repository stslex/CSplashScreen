package com.stslex.csplashscreen.feature.feature_photo_detail.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.ui.theme.AppTheme
import com.stslex.csplashscreen.feature.feature_photo_detail.R

@Composable
fun DownloadImageButton(
    modifier: Modifier = Modifier,
    onDownloadImageClick: () -> Unit
) {
    ExpandButton(
        modifier = modifier,
        painterResource = R.drawable.ic_baseline_arrow_download,
        textResource = R.string.button_title_download,
        onExpandClick = onDownloadImageClick
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun PreviewDownloadImageButton() {
    AppTheme {
        DownloadImageButton(Modifier.padding(32.dp)) {

        }
    }
}
