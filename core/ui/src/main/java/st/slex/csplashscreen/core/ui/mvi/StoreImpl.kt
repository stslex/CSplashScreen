package st.slex.csplashscreen.core.ui.mvi

import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State

internal interface StoreImpl<S : State, in E : Event, A : Action> {

    val initialState: S

    fun sendEvent(event: E)

    fun updateState(update: (S) -> S)
}