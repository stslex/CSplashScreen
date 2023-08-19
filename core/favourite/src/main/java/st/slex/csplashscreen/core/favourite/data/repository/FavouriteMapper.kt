package st.slex.csplashscreen.core.favourite.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import st.slex.csplashscreen.core.favourite.data.datasource.FavouriteEntity
import st.slex.csplashscreen.core.favourite.data.repository.JsonParser.parse
import st.slex.csplashscreen.core.favourite.data.repository.JsonParser.toJson
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel

object FavouriteMapper {

    fun FavouriteEntity.toDomain(): PhotoModel = PhotoModel(
        uuid = uuid,
        url = url,
        username = username,
        userUrl = userUrl,
        downloadUrl = downloadUrl,
        tags = tags.parse<List<String>>()
    )

    fun PhotoModel.toEntity(): FavouriteEntity = FavouriteEntity(
        uuid = uuid,
        url = url,
        username = username,
        userUrl = userUrl,
        downloadUrl = downloadUrl,
        tags = tags.toJson()
    )

    fun PagingData<FavouriteEntity>.toDomain() = this.map { entity ->
        entity.toDomain()
    }
}
