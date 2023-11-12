package st.slex.csplashscreen.core.favourite.data.model

import androidx.paging.PagingData
import androidx.paging.map
import st.slex.csplashscreen.core.database.favourite.FavouriteEntity
import st.slex.csplashscreen.core.favourite.data.model.JsonParser.parse
import st.slex.csplashscreen.core.favourite.data.model.JsonParser.toJson
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.Urls

object FavouriteMapper {

    fun FavouriteEntity.toDomain() = PhotoModel(
        uuid = uuid,
        urls = toUrls(),
        username = username,
        userUrl = userUrl,
        downloadUrl = downloadUrl,
        tags = tags.parse<List<String>>()
    )

    fun PhotoModel.toEntity() = FavouriteEntity(
        uuid = uuid,
        urlFull = urls.full,
        urlRaw = urls.raw,
        urlRegular = urls.regular,
        urlSmall = urls.small,
        urlThumb = urls.thumb,
        username = username,
        userUrl = userUrl,
        downloadUrl = downloadUrl,
        tags = tags.toJson()
    )

    fun FavouriteEntity.toUrls() = Urls(
        raw = urlRaw,
        full = urlFull,
        regular = urlRegular,
        small = urlSmall,
        thumb = urlThumb
    )

    fun PagingData<FavouriteEntity>.toDomain() = this.map { entity ->
        entity.toDomain()
    }
}
