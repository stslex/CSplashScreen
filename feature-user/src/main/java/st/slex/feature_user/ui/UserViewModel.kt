package st.slex.feature_user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_collection.data.QueryCollections
import st.slex.core_collection.ui.QueryCollectionsUseCase
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_photos.ui.QueryPhotosUseCase
import st.slex.feature_user.data.UserDataMapper
import st.slex.feature_user.data.UserRepository
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserDataMapper,
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>,
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>
) : ViewModel() {

    fun setAllQueries(username: String) = viewModelScope.launch(Dispatchers.IO) {
        _queryPhotos.tryEmit(QueryPhotos.UserPhotos(username))
        _queryLikes.tryEmit(QueryPhotos.UserLikes(username))
        _queryCollections.tryEmit(QueryCollections.UserCollections(username))
    }

    fun getUser(username: String): StateFlow<Resource<UserModel>> = flow {
        repository.getUser(username).collect { emit(it.map(mapper = mapper)) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )

    private val _queryPhotos =
        MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryPhotos: StateFlow<QueryPhotos> =
        _queryPhotos.asStateFlow()

    private val _queryCollections = MutableStateFlow<QueryCollections>(QueryCollections.EmptyQuery)
    private val queryCollections: StateFlow<QueryCollections> = _queryCollections.asStateFlow()

    private val _queryLikes =
        MutableStateFlow<QueryPhotos>(QueryPhotos.EmptyQuery)
    private val queryLikes: StateFlow<QueryPhotos> =
        _queryLikes.asStateFlow()

    val collections: StateFlow<PagingData<CollectionModel>> = queryCollections
        .map(::newPagerCollections)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val photos: StateFlow<PagingData<ImageModel>> = queryPhotos
        .map(::newPagerPhotos)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    val likes: StateFlow<PagingData<ImageModel>> = queryLikes
        .map(::newPagerLikes)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private var newPagingCollectionsSource: PagingSource<*, *>? = null
    private var newPagingPhotosSource: PagingSource<*, *>? = null
    private var newPagingLikesSource: PagingSource<*, *>? = null

    private fun newPagerCollections(query: QueryCollections): Pager<Int, CollectionModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingCollectionsSource?.invalidate()
            val queryCollectionsUseCase = queryCollectionsUseCaseProvider.get()
            queryCollectionsUseCase(query).also { newPagingCollectionsSource = it }
        }
    }

    private fun newPagerPhotos(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingPhotosSource = it }
        }
    }

    private fun newPagerLikes(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(PagingConfig(10, enablePlaceholders = false)) {
            newPagingLikesSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingLikesSource = it }
        }
    }
}