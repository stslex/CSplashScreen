package st.slex.csplashscreen.ui.screens.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.model.ui.DownloadModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.DownloadDataMapper
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.data.photo.PhotoRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val downloadMapper: DownloadDataMapper,
    private val downloadImageUseCase: DownloadImageUseCase,
) : ViewModel() {

    fun getUrlAndDownloadImage(id: String) = viewModelScope.launch(Dispatchers.IO) {
        getDownloadUrl(id).collect {
            when (it) {
                is Resource.Success -> {
                    downloadImageUseCase.download(it.data.url, id)
                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {

                }
            }
        }
    }

    fun getCurrentPhoto(id: String): StateFlow<Resource<ImageModel>> = flow {
        repository.getCurrentPhoto(id = id).collect { emit(it.map(photoMapper)) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )

    private suspend fun getDownloadUrl(id: String): StateFlow<Resource<DownloadModel>> =
        repository.getDownloadUrl(id = id)
            .flatMapLatest { flowOf(it.map(downloadMapper)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = Resource.Loading
            )
}