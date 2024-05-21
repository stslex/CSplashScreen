package st.slex.csplashscreen.core.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

class AppDispatcherImpl : AppDispatcher {
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val main: MainCoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default
}