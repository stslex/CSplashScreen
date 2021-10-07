package st.slex.csplashscreen.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.ui.core.UIResponse
import st.slex.csplashscreen.ui.core.UIResult
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val response: UIResponse
) : ViewModel() {

    private val _currentPhoto = MutableSharedFlow<UIResult<ImageModel>>(replay = 0)
    val currentPhoto: SharedFlow<UIResult<ImageModel>>
        get() = _currentPhoto

    fun getCurrentPhoto(id: String) = viewModelScope.launch {
        repository.getCurrentPhoto(id).collect {
            response.getAndMap(repository.getCurrentPhoto(id), mapper = photoMapper).collect {
                _currentPhoto.emit(it)
            }
        }
    }
}