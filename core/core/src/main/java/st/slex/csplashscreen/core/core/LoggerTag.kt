package st.slex.csplashscreen.core.core

class LoggerTag(
    private val tag: String
) {

    fun e(throwable: Throwable, message: String? = null) {
        Logger.e(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    fun d(message: String) {
        Logger.d(
            message = message,
            tag = tag
        )
    }

    fun i(
        message: String,
        throwable: Throwable? = null,
    ) {
        Logger.i(
            message = message,
            tag = tag,
            throwable = throwable
        )
    }
}