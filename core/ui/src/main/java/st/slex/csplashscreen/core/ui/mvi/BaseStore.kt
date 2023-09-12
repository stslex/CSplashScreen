package st.slex.csplashscreen.core.ui.mvi

import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseStoreImpl<S : State, E : Event, A : Action> :
    Store<S, E, A>,
    StoreImpl<S, E, A> {

    private var _scope: CoroutineScope? = null
    val scope: CoroutineScope
        get() = requireNotNull(_scope)

    @Suppress("LeakingThis")
    override val state: MutableStateFlow<S> = MutableStateFlow(initialState)
    override val event: MutableSharedFlow<E> = MutableSharedFlow()

    override fun updateState(update: (S) -> S) {
        state.update(update)
    }

    override fun sendEvent(event: E) {
        scope.launch {
            this@BaseStoreImpl.event.emit(event)
        }
    }

    override fun init(scope: CoroutineScope) {
        _scope = scope
    }
}