package st.slex.feature_main.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.csplashscreen.core.collection.data.QueryCollections.AllCollections
import com.stslex.csplashscreen.core.collection.ui.CollectionModel
import com.stslex.csplashscreen.core.collection.ui.toPresentation
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import com.stslex.csplashscreen.core.photos.data.QueryPhotos.AllPhotos
import com.stslex.csplashscreen.core.photos.ui.PhotoModel
import com.stslex.csplashscreen.core.photos.ui.toPresentation
import st.slex.feature_main.domain.MainScreenInteractor

class MainScreenViewModel(
    private val interactor: MainScreenInteractor,
) : BaseViewModel() {

    val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) {
            interactor.getCollectionsPagingSource(AllCollections)
        }.mapState { it.toPresentation() }

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) {
            interactor.getPhotosPagingSource(AllPhotos)
        }.mapState { data -> data.toPresentation() }

    companion object {

        private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}