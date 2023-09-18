package st.slex.csplashscreen.feature.favourite.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import javax.inject.Inject

class FavouriteInteractorImpl @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) : FavouriteInteractor {

    override val photos: Flow<PagingData<PhotoModel>>
        get() = favouriteRepository.allLikes
}