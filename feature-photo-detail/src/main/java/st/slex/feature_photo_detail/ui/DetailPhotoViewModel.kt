package st.slex.feature_photo_detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import st.slex.core.Resource
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.feature_photo_detail.data.DownloadDataMapper
import st.slex.feature_photo_detail.data.PhotoDataMapper
import st.slex.feature_photo_detail.data.PhotoRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val downloadMapper: DownloadDataMapper,
    private val downloadImageUseCase: DownloadImageUseCase,
) : ViewModel() {

    fun getUrlAndDownloadImage(id: String) {
        downloadedUrl(id).onEach { urlData ->
            if (urlData !is Resource.Success) return@onEach
            downloadImageUseCase.download(urlData.data.url, id)
        }.launchIn(viewModelScope)
    }

    val currentPhoto: (id: String) -> StateFlow<Resource<ImageModel>>
        get() = { id ->
            repository.currentPhoto(id).mapLatest { data ->
                data.map(photoMapper)
            }.primaryFlow
        }

    private val downloadedUrl: (id: String) -> StateFlow<Resource<DownloadModel>>
        get() = { id ->
            repository.downloadedUrl(id).mapLatest { data ->
                data.map(downloadMapper)
            }.primaryFlow
        }

    private val <T> Flow<Resource<T>>.primaryFlow: StateFlow<Resource<T>>
        get() = catch { exception -> emit(Resource.Failure(Exception(exception))) }
            .flowOn(Dispatchers.IO)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = Resource.Loading
            )
}