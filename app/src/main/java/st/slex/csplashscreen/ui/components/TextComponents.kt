package st.slex.csplashscreen.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import st.slex.csplashscreen.ui.theme.Typography

@Composable
fun TextUsernamePrimary(username: String) {
    Text(
        text = username,
        style = Typography.h6,
        maxLines = 1,
        lineHeight = TextUnit.Unspecified,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
fun CollectionTextCard(text: String, style: TextStyle) {
    Text(
        text = text,
        textAlign = TextAlign.Start,
        color = Color.White,
        style = style,
        maxLines = 1
    )
}