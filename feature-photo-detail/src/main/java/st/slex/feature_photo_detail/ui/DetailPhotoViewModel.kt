package st.slex.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import st.slex.core.Resource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_photo_detail.data.PhotoRepository
import javax.inject.Inject

@HiltViewModel
class DetailPhotoViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val downloadImageUseCase: DownloadImageUseCase,
) : BaseViewModel() {

    fun getUrlAndDownloadImage(id: String) {
        repository.getDownloadedUrl(id)
            .onEach { downloadModel ->
                downloadImageUseCase.download(downloadModel.url, id)
            }
            .catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun getPhotoById(id: String): StateFlow<Resource<ImageModel>> =
        repository.getPhotoById(id).primaryStateFlow()
}