package st.slex.csplashscreen.core.favourite.data.model

import com.google.gson.Gson

object JsonParser {

    inline fun <reified T : Any> String.parse(): T = Gson().fromJson(this, T::class.java)

    fun <T : Any> T.toJson(): String = Gson().toJson(this)
}