package st.slex.csplashscreen.feature.home.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.home.domain.MainScreenInteractor
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val interactor: MainScreenInteractor,
    private val navigator: Navigator
) : BaseViewModel() {

    val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllCollections)
        }
            .mapState { collection ->
                collection.toPresentation()
            }

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllPhotos)
        }
            .mapState { image ->
                image.toPresentation()
            }

    fun navigate(screen: NavigationScreen) {
        navigator.navigate(screen)
    }

    companion object {

        private val config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        )
    }
}