package st.slex.core

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object UtilsExtensions {

    val String.convertedUrl: String
        get() = URLEncoder.encode(this, StandardCharsets.UTF_8.displayName())
}