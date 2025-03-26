package st.slex.csplashscreen.core.core

import android.util.Log

object Logger {

    private const val DEFAULT_TAG = "GALLERY"

    fun e(
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

    fun d(
        message: String,
        tag: String? = null,
    ) {
        if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.d(currentTag, message)
    }

    fun i(
        message: String,
        tag: String? = null,
        throwable: Throwable? = null,
    ) {
        if (BuildConfig.DEBUG.not()) return
        val currentTag = "$DEFAULT_TAG:${tag.orEmpty()}"
        Log.i(currentTag, message, throwable)
    }

    fun tag(tag: String): LoggerTag = LoggerTag(tag)
}

