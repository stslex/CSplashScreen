package st.slex.csplashscreen.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.DownloadDataMapper
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.data.photo.PhotoRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val downloadMapper: DownloadDataMapper,
    private val downloadImageUseCase: DownloadImageUseCase,
) : ViewModel() {

    fun getUrlAndDownloadImage(id: String) = viewModelScope.launch(Dispatchers.IO) {
        getDownloadUrl(id).collect { request ->
            when (request) {
                is st.slex.core.Resource.Success -> downloadImageUseCase.download(request.data.url, id)
                is st.slex.core.Resource.Loading -> Unit
                is st.slex.core.Resource.Failure -> Unit
            }
        }
    }

    fun getCurrentPhoto(id: String): StateFlow<st.slex.core.Resource<st.slex.core_network.model.ui.image.ImageModel>> = flow {
        repository.getCurrentPhoto(id = id).collect { emit(it.map(photoMapper)) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = st.slex.core.Resource.Loading
    )

    private suspend fun getDownloadUrl(id: String): StateFlow<st.slex.core.Resource<st.slex.core_network.model.ui.DownloadModel>> =
        repository.getDownloadUrl(id = id)
            .flatMapLatest { flowOf(it.map(downloadMapper)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = st.slex.core.Resource.Loading
            )
}