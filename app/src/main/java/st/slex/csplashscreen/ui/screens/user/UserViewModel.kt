package st.slex.csplashscreen.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.user.UserModel
import st.slex.csplashscreen.data.user.UserDataMapper
import st.slex.csplashscreen.data.user.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserDataMapper
) : ViewModel() {

    fun getUser(username: String): StateFlow<Resource<UserModel>> = flow {
        repository.getUser(username).collect { emit(it.map(mapper = mapper)) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )
}