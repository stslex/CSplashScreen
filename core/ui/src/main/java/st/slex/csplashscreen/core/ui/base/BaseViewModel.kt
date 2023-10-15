package st.slex.csplashscreen.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State

open class BaseViewModel<out S : State, out E : Event, in A : Action>(
    private val store: Store<S, E, A>
) : ViewModel() {

    val state: StateFlow<S> = store.state
    val event: SharedFlow<E> = store.event

    init {
        store.init(viewModelScope)
    }

    fun sendAction(action: A) {
        store.processAction(action)
    }

    override fun onCleared() {
        super.onCleared()
        store.destroy()
    }
}