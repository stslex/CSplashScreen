package st.slex.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
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

    fun <T : Any> Flow<Pager<Int, T>>.pagingFlow(): StateFlow<PagingData<T>> =
        flatMapLatest { pager ->
            pager.flow
        }.makeStateFlow(PagingData.empty())

    fun <T : Any> Flow<T>.makeStateFlow(initialValue: T): StateFlow<T> =
        flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )
}