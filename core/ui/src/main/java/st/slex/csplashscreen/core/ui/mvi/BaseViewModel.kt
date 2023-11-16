package st.slex.csplashscreen.core.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.ui.mvi.Store.Action
import st.slex.csplashscreen.core.ui.mvi.Store.Event
import st.slex.csplashscreen.core.ui.mvi.Store.Navigation
import st.slex.csplashscreen.core.ui.mvi.Store.State

abstract class BaseViewModel<S : State, E : Event, A : Action, N : Navigation>(
    private val router: Router<N>,
    private val appDispatcher: AppDispatcher
) : ViewModel() {

    abstract val initialState: S
    abstract fun sendAction(action: A)

    protected open val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S>
        get() = _state.asStateFlow()

    val event: MutableSharedFlow<E> = MutableSharedFlow()

    fun updateState(update: (S) -> S) {
        _state.update(update)
    }

    fun sendEvent(event: E) {
        viewModelScope.launch(appDispatcher.default) {
            this@BaseViewModel.event.emit(event)
        }
    }

    fun navigate(event: N) {
        router(event)
    }

    fun <K : Any, T : Any, R : Any> Pager<K, T>.state(
        transform: suspend (value: T) -> R
    ): StateFlow<PagingData<R>> = this
        .flow
        .map { pagingData -> pagingData.map(transform) }
        .state()

    fun <T : Any> Flow<PagingData<T>>.state(): StateFlow<PagingData<T>> = this
        .flowOn(appDispatcher.default)
        .cachedIn(viewModelScope)
        .stateIn(
            initialValue = PagingData.empty(),
            scope = viewModelScope,
            started = SharingStarted.Lazily
        )

    fun launch(
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(
        context = appDispatcher.default,
        block = block
    )

    fun <T> launchCatching(
        block: suspend CoroutineScope.() -> T,
        onFailure: suspend (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit,
    ): Job = viewModelScope.launch(appDispatcher.default) {
        runCatching {
            block()
        }
            .onSuccess(onSuccess)
            .onFailure { error ->
                onFailure(error)
                Logger.exception(error)
            }
    }

    fun <T> Flow<T>.launch(
        onError: suspend (cause: Throwable) -> Unit = {},
        each: suspend (T) -> Unit
    ): Job = this
        .flowOn(appDispatcher.default)
        .catch { error ->
            onError(error)
            Logger.exception(error)
        }
        .onEach(each)
        .launchIn(viewModelScope)
}