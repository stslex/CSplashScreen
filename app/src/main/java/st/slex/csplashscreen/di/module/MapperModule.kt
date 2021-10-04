package st.slex.csplashscreen.di.module

import dagger.Module
import dagger.Provides
import st.slex.csplashscreen.data.core.Mapper
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.model.ui.DownloadModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photo.DownloadDataMapper
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.ui.core.UIResult

@Module
class MapperModule {

    @Provides
    fun providesPhotoDataMapper(): Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>> =
        PhotoDataMapper()

    @Provides
    fun providesDownloadDataMapper(): Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>> =
        DownloadDataMapper()
}