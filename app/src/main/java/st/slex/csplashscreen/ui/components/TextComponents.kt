package st.slex.csplashscreen.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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

@Composable
fun MyAppTextFieldColors(
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.DarkGray,
    backgroundColor: Color = Color.White,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.Black,
    focusedIndicatorColor: Color = Color.Black,
    unfocusedIndicatorColor: Color = Color.Black,
    disabledIndicatorColor: Color = Color.Black,
    errorIndicatorColor: Color = Color.Black,
    leadingIconColor: Color = Color.Black,
    disabledLeadingIconColor: Color = Color.Black,
    errorLeadingIconColor: Color = Color.Black,
    trailingIconColor: Color = Color.Black,
    disabledTrailingIconColor: Color = Color.Black,
    errorTrailingIconColor: Color = Color.Black,
    focusedLabelColor: Color = Color.Black,
    unfocusedLabelColor: Color = Color.Black,
    disabledLabelColor: Color = Color.Black,
    errorLabelColor: Color = Color.Black,
    placeholderColor: Color = Color.Black,
    disabledPlaceholderColor: Color = Color.Black
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,
    focusedIndicatorColor = focusedIndicatorColor,
    unfocusedIndicatorColor = unfocusedIndicatorColor,
    disabledIndicatorColor = disabledIndicatorColor,
    errorIndicatorColor = errorIndicatorColor,
    leadingIconColor = leadingIconColor,
    disabledLeadingIconColor = disabledLeadingIconColor,
    errorLeadingIconColor = errorLeadingIconColor,
    trailingIconColor = trailingIconColor,
    disabledTrailingIconColor = disabledTrailingIconColor,
    errorTrailingIconColor = errorTrailingIconColor,
    focusedLabelColor = focusedLabelColor,
    unfocusedLabelColor = unfocusedLabelColor,
    disabledLabelColor = disabledLabelColor,
    errorLabelColor = errorLabelColor,
    placeholderColor = placeholderColor,
    disabledPlaceholderColor = disabledPlaceholderColor
)