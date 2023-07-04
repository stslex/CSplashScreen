package st.slex.feature_collection.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import st.slex.core_navigation.AppArguments
import st.slex.core_navigation.NavigationScreen
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_photos.data.QueryPhotos
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.feature_collection.domain.SingleCollectionInteractor

class SingleCollectionViewModel(
    private val interactor: SingleCollectionInteractor,
    args: AppArguments.CollectionScreen,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    private val _queryPhotos =
        MutableStateFlow<QueryPhotos>(QueryPhotos.CollectionPhotos(args.collectionId))
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    @ExperimentalCoroutinesApi
    val photos: StateFlow<PagingData<ImageModel>> = queryPhotos.map(::newPagerPhotos).pagingFlow

    private var newPagingPhotosSource: PagingSource<*, *>? = null

    fun setQueryPhotos(query: QueryPhotos) {
        _queryPhotos.tryEmit(query)
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            interactor.getPhotosPagingSource(query).also { newPagingPhotosSource = it }
        }
    }

    fun onProfileClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, imageId: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, imageId))
    }
}