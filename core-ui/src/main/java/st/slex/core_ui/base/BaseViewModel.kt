package st.slex.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import st.slex.core.Resource

open class BaseViewModel : ViewModel() {

    fun <T> Flow<T>.primaryStateFlow(): StateFlow<Resource<T>> =
        mapLatest<T, Resource<T>> { data ->
            Resource.Success(data)
        }.catch { exception ->
            emit(Resource.Failure(Exception(exception)))
        }.makeStateFlow(Resource.Loading)

    val <T : Any> Pager<Int, T>.pagingFlow: StateFlow<PagingData<T>>
        get() = flow.primaryPagingFlow

    val <T : Any> Flow<Pager<Int, T>>.pagingFlow: StateFlow<PagingData<T>>
        get() = flatMapLatest { pager -> pager.flow }.primaryPagingFlow

    private val <T : Any> Flow<PagingData<T>>.primaryPagingFlow: StateFlow<PagingData<T>>
        get() = cachedIn(viewModelScope).makeStateFlow(PagingData.empty())

    fun <T : Any> Flow<T>.makeStateFlow(initialValue: T): StateFlow<T> =
        flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )
}