package st.slex.csplashscreen.core.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State

interface Store<out S : State, out E : Event, in A : Action> {

    val state: StateFlow<S>
    val event: SharedFlow<E>

    fun processAction(action: A)

    fun init(scope: CoroutineScope)

    fun destroy()

    interface State

    interface Event

    interface Navigation

    interface Action
}
