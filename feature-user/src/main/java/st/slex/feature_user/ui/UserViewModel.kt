package st.slex.feature_user.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_user.domain.UserInteractor

class UserViewModel(
    private val interactor: UserInteractor
) : BaseViewModel() {

    fun setAllQueries(username: String) = viewModelScope.launch(Dispatchers.IO) {
        _queryPhotos.tryEmit(QueryPhotos.UserPhotos(username))
        _queryLikes.tryEmit(QueryPhotos.UserLikes(username))
        _queryCollections.tryEmit(QueryCollections.UserCollections(username))
    }

    fun getUser(username: String): StateFlow<Resource<UserModel>> =
        interactor.getUser(username).primaryStateFlow()

    private val _queryPhotos = MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> = _queryPhotos.asStateFlow()

    private val _queryCollections = MutableStateFlow<QueryCollections>(QueryCollections.EmptyQuery)
    private val queryCollections: StateFlow<QueryCollections> = _queryCollections.asStateFlow()

    private val _queryLikes =
        MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
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
}