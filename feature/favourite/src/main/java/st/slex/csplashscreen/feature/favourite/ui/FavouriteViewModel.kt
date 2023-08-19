package st.slex.csplashscreen.feature.favourite.ui

import androidx.paging.PagingData
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import kotlinx.coroutines.flow.StateFlow

class FavouriteViewModel(
    private val interactor: FavouriteInteractor,
    private val navigator: Navigator,
) : BaseViewModel() {

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = interactor.photos.primaryPagingFlow

    fun onUserClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(uuid: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(uuid))
    }

    fun onGoToPhotosClick() {
        navigator.navigate(NavigationScreen.Home)
    }
}