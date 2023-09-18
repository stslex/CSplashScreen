package st.slex.csplashscreen.core.favourite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.favourite.data.datasource.FavouriteDao
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteMapper.toDomain
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteMapper.toEntity
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteDao
) : FavouriteRepository {

    override val allLikes: Flow<PagingData<PhotoModel>>
        get() = Pager(
            config = pagingConfig,
            pagingSourceFactory = dao::getAll
        )
            .flow
            .map { pagingData ->
                pagingData.toDomain()
            }
            .flowOn(Dispatchers.IO)

    override suspend fun remove(uuid: String) {
        withContext(Dispatchers.IO) {
            dao.remove(uuid)
        }
    }

    override suspend fun add(photoModel: PhotoModel) {
        withContext(Dispatchers.IO) {
            dao.add(photoModel.toEntity())
        }
    }

    override fun isLiked(
        uuid: String
    ): Flow<Boolean> = dao.getFlow(uuid)
        .map { image ->
            image != null
        }
        .flowOn(Dispatchers.IO)

    override suspend fun getItem(
        uuid: String
    ): PhotoModel? = withContext(Dispatchers.IO) {
        dao.getItem(uuid)
            ?.toDomain()
    }

    companion object {

        private val pagingConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}