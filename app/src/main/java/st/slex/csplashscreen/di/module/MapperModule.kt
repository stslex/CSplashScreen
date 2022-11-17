package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.feature_user.data.UserDataMapper

@InstallIn(SingletonComponent::class)
@Module
interface MapperModule {

    @Binds
    fun bindsUserDataMapper(mapper: UserDataMapper.Base): UserDataMapper
}