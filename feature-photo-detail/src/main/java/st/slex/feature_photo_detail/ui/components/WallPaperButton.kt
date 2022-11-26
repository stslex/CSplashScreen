package st.slex.feature_photo_detail.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import st.slex.core_ui.theme.AppTheme
import st.slex.feature_photo_detail.R

@Composable
fun WallPaperButton(
    modifier: Modifier = Modifier,
    onSetWallpaperClick: () -> Unit
) {
    ExpandButton(
        modifier = modifier,
        painterResource = R.drawable.ic_baseline_wallpaper,
        textResource = R.string.button_title_wallpaper,
        onExpandClick = onSetWallpaperClick
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun PreviewWallPaperButton() {
    AppTheme {
        WallPaperButton(Modifier.padding(32.dp)) {

        }
    }
}