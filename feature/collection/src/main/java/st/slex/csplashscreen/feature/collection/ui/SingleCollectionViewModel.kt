package st.slex.csplashscreen.feature.collection.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import kotlinx.coroutines.flow.StateFlow

class SingleCollectionViewModel(
    private val interactor: SingleCollectionInteractor,
    private val navigator: Navigator,
    args: AppArguments.CollectionScreen,
) : BaseViewModel() {

    val photos: StateFlow<PagingData<PhotoModel>> = Pager(pagingConfig) {
        PagingSource { page, pageSize ->
            interactor.getPhotos(
                uuid = args.collectionId,
                page = page,
                pageSize = pageSize
            )
        }
    }
        .mapState { image ->
            image.toPresentation()
        }

    fun onProfileClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(imageId: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(imageId))
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 2,
            enablePlaceholders = false
        )
    }
}