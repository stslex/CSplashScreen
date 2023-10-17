package st.slex.csplashscreen.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.ui.mvi.Router
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State

open class BaseViewModel<out S : State, out E : Event, in A : Action, in N : Event.Navigation>(
    private val store: Store<S, E, A>,
    private val router: Router<N>
) : ViewModel() {

    val state: StateFlow<S> = store.state
    val event: SharedFlow<E> = store.event

    init {
        store.init(viewModelScope)
    }

    fun sendAction(action: A) {
        store.processAction(action)
    }

    fun navigate(event: N) {
        router(event)
    }

    override fun onCleared() {
        super.onCleared()
        store.destroy()
    }
}