package st.slex.csplashscreen.core.core

import android.util.Log

object Logger {

    private const val DEFAULT_TAG = "GALLERY"

    fun exception(
        throwable: Throwable,
        tag: String? = null,
        message: String? = null
    ) {
        if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.e(
            currentTag,
            message ?: throwable.message.orEmpty(),
            throwable,
        )
    }

    fun debug(
        message: String,
        tag: String? = null,
    ) {
        if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.d(currentTag, message)
    }
}