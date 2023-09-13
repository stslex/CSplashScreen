package st.slex.csplashscreen.feature.home.ui.store

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.home.domain.HomeInteractor
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Action
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Event
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.State
import javax.inject.Inject

class HomeStoreImpl @Inject constructor(
    private val interactor: HomeInteractor
) : HomeStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        collections = ::collections,
        photos = ::photos
    )

    private val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllCollections)
        }
            .flow
            .map { pagingData -> pagingData.map { it.toPresentation() } }
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) {
            PagingSource(interactor::getAllPhotos)
        }
            .flow
            .map { pagingData -> pagingData.map { it.toPresentation() } }
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    override fun processAction(action: Action) {
        TODO("Not yet implemented")
    }


    companion object {

        private val config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = false
        )
    }
}