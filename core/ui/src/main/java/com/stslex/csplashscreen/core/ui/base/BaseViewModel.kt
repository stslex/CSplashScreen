package com.stslex.csplashscreen.core.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex.csplashscreen.core.core.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.getScopeName

open class BaseViewModel : ViewModel() {

    val coroutineHandler = CoroutineExceptionHandler { context, throwable ->
        onError(throwable, context.getScopeName().value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <T> Flow<T>.primaryStateFlow(): StateFlow<Resource<T>> = this
        .mapLatest<T, Resource<T>> { data ->
            Resource.Success(data)
        }
        .catch { exception ->
            emit(Resource.Failure(Exception(exception)))
        }
        .makeStateFlow(Resource.Loading)

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

    val <T : Any> Pager<Int, T>.pagingFlow: StateFlow<PagingData<T>>
        get() = flow.primaryPagingFlow

    @OptIn(ExperimentalCoroutinesApi::class)
    val <T : Any> Flow<Pager<Int, T>>.pagingFlow: StateFlow<PagingData<T>>
        get() = flatMapLatest { pager -> pager.flow }.primaryPagingFlow

    val <T : Any> Flow<PagingData<T>>.primaryPagingFlow: StateFlow<PagingData<T>>
        get() = cachedIn(viewModelScope).makeStateFlow(PagingData.empty())

    private fun <T : Any> Flow<T>.makeStateFlow(initialValue: T): StateFlow<T> =
        flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )

    fun onError(throwable: Throwable, localisation: String) {
        Log.e(localisation, throwable.message, throwable.cause)
    }
}