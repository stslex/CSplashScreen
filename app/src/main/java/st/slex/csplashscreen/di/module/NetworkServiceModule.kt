package st.slex.csplashscreen.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import st.slex.core_network.service.CollectionService
import st.slex.core_network.service.PhotosService
import st.slex.core_network.service.SearchService
import st.slex.core_network.service.TopicsService

@InstallIn(SingletonComponent::class)
@Module
class NetworkServiceModule {

    @Provides
    fun providesPhotosService(retrofit: Retrofit): PhotosService =
        retrofit.create(PhotosService::class.java)

    @Provides
    fun providesCollectionsService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)

    @Provides
    fun providesPhotoSearchService(retrofit: Retrofit): SearchService = retrofit.create(
        SearchService::class.java
    )

    @Provides
    fun providesTopicsService(retrofit: Retrofit): TopicsService =
        retrofit.create(TopicsService::class.java)
}