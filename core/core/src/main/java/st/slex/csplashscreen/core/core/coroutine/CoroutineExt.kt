package st.slex.csplashscreen.core.core.coroutine

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine as combinePrimary
import kotlinx.coroutines.flow.map as mapPrimary

object CoroutineExt {

    fun <T, R> StateFlow<T>.mapState(
        transform: (a: T) -> R,
    ): StateFlow<R> = TransformableStateFlow(
        getValue = { transform(this.value) },
        flow = mapPrimary(transform = transform),
    )

    fun <T1, T2, R> StateFlow<T1>.combineState(
        flow: StateFlow<T2>,
        transform: (a: T1, b: T2) -> R,
    ): StateFlow<R> = TransformableStateFlow(
        getValue = { transform(this.value, flow.value) },
        flow = combinePrimary(
            flow = flow,
            transform = transform,
        ),
    )
}
