package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.csplashscreen.data.photo.DownloadDataMapper
import st.slex.csplashscreen.data.photo.PhotoDataMapper
import st.slex.csplashscreen.data.user.UserDataMapper

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