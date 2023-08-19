package com.stslex.csplashscreen.feature.favourite.ui

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.navigator.Navigator
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.favourite.domain.FavouriteInteractor
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
}