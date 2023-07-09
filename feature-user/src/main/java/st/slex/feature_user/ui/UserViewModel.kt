package st.slex.feature_user.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import com.stslex.csplashscreen.core.core.Resource
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import com.stslex.csplashscreen.core.photos.data.QueryPhotos
import st.slex.feature_user.domain.UserInteractor

class UserViewModel(
    private val interactor: UserInteractor,
    private val args: AppArguments.UserScreen,
    private val navigate: (NavigationScreen) -> Unit
) : BaseViewModel() {

    val username: String
        get() = args.username

    val user: StateFlow<Resource<UserModel>>
        get() = interactor.getUser(username).primaryStateFlow()

    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.UserPhotos(username))
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    private val _queryCollections =
        MutableStateFlow<QueryCollections>(QueryCollections.UserCollections(username))
    private val queryCollections: StateFlow<QueryCollections> = _queryCollections.asStateFlow()

    private val _queryLikes = MutableStateFlow<QueryPhotos>(QueryPhotos.UserLikes(username))
    private val queryLikes: StateFlow<QueryPhotos> =
        _queryLikes.asStateFlow()

    val collections: StateFlow<PagingData<CollectionDomainModel>> =
        queryCollections.map(::newPagerCollections).pagingFlow

    val photos: StateFlow<PagingData<ImageModel>> =
        queryPhotos.map(::newPagerPhotos).pagingFlow

    @OptIn(ExperimentalCoroutinesApi::class)
    val likes: StateFlow<PagingData<ImageModel>> = queryLikes
        .map(::newPagerLikes)
        .flatMapLatest { pager ->
            pager.flow
        }
        .primaryPagingFlow

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null
    private var newPagingLikesSource: PagingSource<*, *>? = null

    private fun newPagerCollections(query: QueryCollections): Pager<Int, CollectionDomainModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingCollectionsSource?.invalidate()
            interactor.getCollectionsPagingSource(query).also { newPagingCollectionsSource = it }
        }
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            interactor.getPhotosPagingSource(query).also { newPagingPhotosSource = it }
        }
    }

    private fun newPagerLikes(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingLikesSource?.invalidate()
            interactor.getPhotosPagingSource(query).also { newPagingLikesSource = it }
        }
    }

    fun popBackStack() {
        navigate(NavigationScreen.PopBackStack)
    }

    fun onUserClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onImageClick(url: String, id: String) {
        navigate(NavigationScreen.ImageDetailScreen(url, id))
    }

    fun onCollectionClick(id: String) {
        navigate(NavigationScreen.CollectionScreen(id))
    }
}