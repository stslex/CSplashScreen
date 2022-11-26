package st.slex.feature_user.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import st.slex.core.Resource
import st.slex.core_collection.data.QueryCollections
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_user.domain.UserInteractor
import st.slex.feature_user.navigation.UserRouter

class UserViewModel(
    private val interactor: UserInteractor,
    private val router: UserRouter,
    private val args: AppArguments.UserScreen
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

    val collections: StateFlow<PagingData<CollectionModel>> =
        queryCollections.map(::newPagerCollections).pagingFlow

    val photos: StateFlow<PagingData<ImageModel>> =
        queryPhotos.map(::newPagerPhotos).pagingFlow

    val likes: StateFlow<PagingData<ImageModel>> =
        queryLikes.map(::newPagerLikes).pagingFlow

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null
    private var newPagingLikesSource: PagingSource<*, *>? = null

    private fun newPagerCollections(query: QueryCollections): Pager<Int, CollectionModel> {
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
        router.popBackStack()
    }

    fun onUserClick(username: String) {
        router.navToProfile(username)
    }

    fun onImageClick(url: String, id: String) {
        router.navToDetailImage(url, id)
    }

    fun onCollectionClick(id: String) {
        router.navToCollection(id)
    }
}