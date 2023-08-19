package st.slex.csplashscreen.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Immutable
data class BackgroundTheme(
    val color: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified,
    val primaryGradientColor: Color = Color.Unspecified,
    val secondaryGradientColor: Color = Color.Unspecified,
    val tertiaryGradientColor: Color = Color.Unspecified,
    val neutralGradientColor: Color = Color.Unspecified
)

val LocalBackgroundTheme = staticCompositionLocalOf { BackgroundTheme() }