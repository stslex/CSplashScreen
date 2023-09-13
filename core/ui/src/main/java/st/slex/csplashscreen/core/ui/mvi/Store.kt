package st.slex.csplashscreen.core.ui.mvi

import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Store<out S : State, out E : Event, in A : Action> {

    val state: StateFlow<S>
    val event: SharedFlow<E>

    fun processAction(action: A)

    fun init(scope: CoroutineScope)

    interface State
    interface Event
    interface Action
}
