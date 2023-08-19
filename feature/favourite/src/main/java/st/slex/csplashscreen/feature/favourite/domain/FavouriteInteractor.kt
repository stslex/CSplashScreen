package st.slex.csplashscreen.feature.favourite.domain

import androidx.paging.PagingData
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface FavouriteInteractor {

    val photos: Flow<PagingData<PhotoModel>>
}