package st.slex.csplashscreen.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import st.slex.csplashscreen.ui.theme.Typography

@Composable
fun TextUsernamePrimary(username: String) {
    Text(
        text = username,
        style = Typography.labelMedium,
        maxLines = 1,
        lineHeight = TextUnit.Unspecified
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
fun setTextFieldColors(isDarkTheme: Boolean = isSystemInDarkTheme()) =
    if (isDarkTheme) myAppTextFieldDarkColors() else myAppTextFieldColors()

@Composable
fun myAppTextFieldColors(
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.DarkGray,
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = Color.DarkGray,
    errorCursorColor: Color = Color.Black,
    focusedIndicatorColor: Color = Color.DarkGray,
    unfocusedIndicatorColor: Color = Color.LightGray,
    disabledIndicatorColor: Color = Color.LightGray,
    errorIndicatorColor: Color = Color.Black,
    leadingIconColor: Color = Color.Black,
    disabledLeadingIconColor: Color = Color.Black,
    errorLeadingIconColor: Color = Color.Black,
    trailingIconColor: Color = Color.Black,
    disabledTrailingIconColor: Color = Color.Black,
    errorTrailingIconColor: Color = Color.Black,
    focusedLabelColor: Color = Color.DarkGray,
    unfocusedLabelColor: Color = Color.LightGray,
    disabledLabelColor: Color = Color.LightGray,
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

@Composable
fun myAppTextFieldDarkColors(
    textColor: Color = Color.White,
    disabledTextColor: Color = Color.LightGray,
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = Color.LightGray,
    errorCursorColor: Color = Color.Red,
    focusedIndicatorColor: Color = Color.LightGray,
    unfocusedIndicatorColor: Color = Color.DarkGray,
    disabledIndicatorColor: Color = Color.DarkGray,
    errorIndicatorColor: Color = Color.White,
    leadingIconColor: Color = Color.White,
    disabledLeadingIconColor: Color = Color.White,
    errorLeadingIconColor: Color = Color.White,
    trailingIconColor: Color = Color.White,
    disabledTrailingIconColor: Color = Color.White,
    errorTrailingIconColor: Color = Color.White,
    focusedLabelColor: Color = Color.LightGray,
    unfocusedLabelColor: Color = Color.DarkGray,
    disabledLabelColor: Color = Color.DarkGray,
    errorLabelColor: Color = Color.White,
    placeholderColor: Color = Color.White,
    disabledPlaceholderColor: Color = Color.White
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