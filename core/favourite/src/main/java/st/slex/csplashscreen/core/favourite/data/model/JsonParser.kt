package st.slex.csplashscreen.core.favourite.data.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonParser {

    val jsonParser by lazy {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    inline fun <reified T : Any> String.parse(): T = jsonParser.decodeFromString(this)

    inline fun <reified T : Any> T.toJson(): String = jsonParser.encodeToString(this)
}