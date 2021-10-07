package st.slex.csplashscreen.ui.screens.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.ui.core.UIResult
import st.slex.csplashscreen.ui.navigation.NavigationActions
import st.slex.csplashscreen.ui.navigation.NavigationState
import st.slex.csplashscreen.ui.navigation.Navigator
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailPhotoViewModel @Inject constructor(
    private val navigator: Navigator,
    private val actions: NavigationActions,
    private val repository: PhotoRepository,
    private val mapper: PhotoDataMapper,
) : ViewModel() {

    fun navigate(destination: NavigationState, args: List<String>) {
        navigator.navigate(actions.navigation(destination, args))
    }

    fun getCurrentPhoto(id: String): Flow<UIResult<ImageModel>> = flow {
        emit(UIResult.Loading)
        try {
            repository.getCurrentPhoto(id).collect {
                emit(it.map(mapper = mapper))
            }
        } catch (exception: Exception) {
            emit(UIResult.Failure(exception = exception))
        }
    }
}