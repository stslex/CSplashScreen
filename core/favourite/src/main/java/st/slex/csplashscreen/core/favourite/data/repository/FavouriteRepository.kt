package st.slex.csplashscreen.core.favourite.data.repository

import androidx.paging.PagingData
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val allLikes: Flow<PagingData<PhotoModel>>

    suspend fun remove(uuid: String)

    suspend fun add(photoModel: PhotoModel)

    fun isLiked(uuid: String): Flow<Boolean>

    suspend fun getItem(uuid: String): PhotoModel?
}