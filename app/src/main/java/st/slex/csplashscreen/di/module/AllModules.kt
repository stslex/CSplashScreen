package st.slex.csplashscreen.di.module

import androidx.paging.PagingSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core_collection.data.CollectionsPagingSource
import st.slex.core_collection.data.CollectionsRepository
import st.slex.core_collection.data.CollectionsRepositoryImpl
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.client.NetworkClientImpl
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.core_network.source.interf.CollectionNetworkSource
import st.slex.core_network.source.interf.PagingPhotosNetworkSource
import st.slex.core_network.source.interf.PhotoNetworkSource
import st.slex.core_network.source.interf.SearchPhotosNetworkSource
import st.slex.core_network.source.interf.TopicsNetworkSource
import st.slex.core_network.source.interf.UserNetworkSource
import st.slex.core_network.source.real.CollectionNetworkSourceImpl
import st.slex.core_network.source.real.PagingPhotosNetworkSourceImpl
import st.slex.core_network.source.real.PhotoNetworkSourceImpl
import st.slex.core_network.source.real.SearchPhotosNetworkSourceImpl
import st.slex.core_network.source.real.TopicsNetworkSourceImpl
import st.slex.core_network.source.real.UserNetworkSourceImpl
import st.slex.core_photos.data.PhotosPagingSource
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.PhotosRepositoryImpl
import st.slex.feature_collection.domain.SingleCollectionInteractor
import st.slex.feature_collection.domain.SingleCollectionInteractorImpl
import st.slex.feature_collection.ui.SingleCollectionViewModel
import st.slex.feature_main.domain.MainScreenInteractor
import st.slex.feature_main.domain.MainScreenInteractorImpl
import st.slex.feature_main.navigation.MainScreenRouter
import st.slex.feature_main.navigation.MainScreenRouterImpl
import st.slex.feature_main.ui.MainScreenViewModel
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.data.PhotoRepositoryImpl
import st.slex.feature_photo_detail.ui.DetailPhotoViewModel
import st.slex.feature_photo_detail.ui.DownloadImageUseCase
import st.slex.feature_search_photos.data.SearchPagingSource
import st.slex.feature_search_photos.data.SearchRepository
import st.slex.feature_search_photos.data.SearchRepositoryImpl
import st.slex.feature_search_photos.domain.SearchPhotosInteractor
import st.slex.feature_search_photos.domain.SearchPhotosInteractorImpl
import st.slex.feature_search_photos.ui.SearchViewModel
import st.slex.feature_topics.data.TopicsPagingSource
import st.slex.feature_topics.ui.TopicsViewModel
import st.slex.feature_user.data.UserRepository
import st.slex.feature_user.data.UserRepositoryImpl
import st.slex.feature_user.domain.UserInteractor
import st.slex.feature_user.domain.UserInteractorImpl
import st.slex.feature_user.ui.UserViewModel

class AllModules {
    val allModules = module {

        /*NetworkModule*/
        singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
        singleOf(::PhotoNetworkSourceImpl) { bind<PhotoNetworkSource>() }
        singleOf(::UserNetworkSourceImpl) { bind<UserNetworkSource>() }
        singleOf(::TopicsNetworkSourceImpl) { bind<TopicsNetworkSource>() }
        singleOf(::PagingPhotosNetworkSourceImpl) { bind<PagingPhotosNetworkSource>() }
        singleOf(::SearchPhotosNetworkSourceImpl) { bind<SearchPhotosNetworkSource>() }
        singleOf(::CollectionNetworkSourceImpl) { bind<CollectionNetworkSource>() }

        /*REPOSITORY*/
        singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
        singleOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
        singleOf(::PhotoRepositoryImpl) { bind<PhotoRepository>() }
        singleOf(::SearchRepositoryImpl) { bind<SearchRepository>() }
        singleOf(::UserRepositoryImpl) { bind<UserRepository>() }

        /*Utils*/
        singleOf(DownloadImageUseCase::Base) { bind<DownloadImageUseCase>() }

        /*ViewModels*/
        viewModelOf(::MainScreenViewModel)
        viewModelOf(::DetailPhotoViewModel)
        viewModelOf(::SearchViewModel)
        viewModelOf(::UserViewModel)
        viewModelOf(::SingleCollectionViewModel)
        viewModelOf(::TopicsViewModel)

        /*PagingSource*/
        singleOf(PhotosPagingSource::Factory)
        singleOf(SearchPagingSource::Factory)
        singleOf(CollectionsPagingSource::Factory)
        singleOf(::TopicsPagingSource) { bind<PagingSource<Int, TopicsModel>>() }

        /*Interactor*/
        factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }
        factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
        factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
        factoryOf(::SearchPhotosInteractorImpl) { bind<SearchPhotosInteractor>() }

        /*Router*/
        factoryOf(::MainScreenRouterImpl) { bind<MainScreenRouter>() }
    }
}
