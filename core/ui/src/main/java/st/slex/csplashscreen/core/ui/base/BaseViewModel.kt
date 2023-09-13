package st.slex.csplashscreen.core.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    val coroutineHandler = CoroutineExceptionHandler { context, throwable ->
        onError(throwable, context.toString())
    }

    inline fun <T : Any, R : Any> Pager<Int, T>.mapState(
        crossinline transform: suspend (T) -> R
    ): StateFlow<PagingData<R>> = this
        .flow
        .map { pagingData ->
            pagingData.map { item ->
                transform(item)
            }
        }
        .primaryPagingFlow

    @OptIn(ExperimentalCoroutinesApi::class)
    inline fun <T : Any, R : Any> Flow<Pager<Int, T>>.mapState(
        crossinline transform: suspend (T) -> R
    ): StateFlow<PagingData<R>> = this
        .flatMapLatest { pager ->
            pager.flow.map { pagingData ->
                pagingData.map { item ->
                    transform(item)
                }
            }
        }
        .primaryPagingFlow

    val <T : Any> Pager<Int, T>.pagingFlow: StateFlow<PagingData<T>>
        get() = flow.primaryPagingFlow

    @OptIn(ExperimentalCoroutinesApi::class)
    val <T : Any> Flow<Pager<Int, T>>.pagingFlow: StateFlow<PagingData<T>>
        get() = flatMapLatest { pager -> pager.flow }
            .primaryPagingFlow

    val <T : Any> Flow<PagingData<T>>.primaryPagingFlow: StateFlow<PagingData<T>>
        get() = cachedIn(viewModelScope)
            .makeStateFlow(PagingData.empty())

    private fun <T : Any> Flow<T>.makeStateFlow(initialValue: T): StateFlow<T> =
        flowOn(Dispatchers.IO)
            .stateIn(
                initialValue = initialValue
            )

    fun <T : Any> Flow<T>.stateIn(initialValue: T): StateFlow<T> =
        stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )

    fun onError(throwable: Throwable, localisation: String) {
        Log.e(localisation, throwable.message, throwable.cause)
    }
}