package st.slex.csplashscreen.core.core.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

class TransformableStateFlow<out T>(
    val getValue: () -> T,
    val flow: Flow<T>,
) : StateFlow<T> {

    override val replayCache: List<T>
        get() = listOf(value)

    override val value: T
        get() = getValue()

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        coroutineScope {
            flow.distinctUntilChanged()
                .stateIn(this)
                .collect(collector)
        }
    }
}