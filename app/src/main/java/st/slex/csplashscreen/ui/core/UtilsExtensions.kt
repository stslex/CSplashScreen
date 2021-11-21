package st.slex.csplashscreen.ui.core

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object UtilsExtensions {

    fun String.convertUrl() = URLEncoder.encode(this, StandardCharsets.UTF_8.displayName())
}