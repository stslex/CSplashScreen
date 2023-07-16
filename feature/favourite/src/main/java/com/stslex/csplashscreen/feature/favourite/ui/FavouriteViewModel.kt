package com.stslex.csplashscreen.feature.favourite.ui

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import kotlinx.coroutines.flow.StateFlow

class FavouriteViewModel(
    private val navigate: (NavigationScreen) -> Unit,
    private val interactor: FavouriteInteractor
) : BaseViewModel() {

    val photos: StateFlow<PagingData<PhotoModel>>
        get() = interactor.photos.primaryPagingFlow

    fun onUserClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(uuid: String) {
        navigate(NavigationScreen.ImageDetailScreen(uuid))
    }
}