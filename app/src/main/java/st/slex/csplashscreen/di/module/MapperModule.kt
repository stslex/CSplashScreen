package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.feature_photo_detail.data.DownloadDataMapper
import st.slex.feature_photo_detail.data.PhotoDataMapper
import st.slex.feature_user.data.UserDataMapper

@InstallIn(SingletonComponent::class)
@Module
interface MapperModule {

    @Binds
    fun bindsPhotoDataMapper(mapper: PhotoDataMapper.Base): PhotoDataMapper

    @Binds
    fun bindsDownloadDataMapper(mapper: DownloadDataMapper.Base): DownloadDataMapper

    @Binds
    fun bindsUserDataMapper(mapper: UserDataMapper.Base): UserDataMapper
}