package st.slex.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import st.slex.core.Resource

open class BaseViewModel : ViewModel() {

    val <T> Flow<T>.primaryFlow: StateFlow<Resource<T>>
        get():StateFlow<Resource<T>> = mapLatest<T, Resource<T>> { data ->
            Resource.Success(data)
        }.catch { exception ->
            emit(Resource.Failure(Exception(exception)))
        }.flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )
}