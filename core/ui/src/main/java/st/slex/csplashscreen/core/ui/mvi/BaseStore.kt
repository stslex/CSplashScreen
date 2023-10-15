package st.slex.csplashscreen.core.ui.mvi

import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.State

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

    override fun destroy() {
        _scope?.cancel()
        _scope = null
    }

    fun <T : Any> Flow<PagingData<T>>.state(): StateFlow<PagingData<T>> = this
        .flowOn(Dispatchers.IO)
        .cachedIn(scope)
        .stateIn(
            initialValue = PagingData.empty(),
            scope = scope,
            started = SharingStarted.Lazily
        )
}