package st.slex.csplashscreen.feature.home.ui.presenter

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.collection.ui.model.toPresentation
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation
import st.slex.csplashscreen.core.ui.mvi.BaseViewModel
import st.slex.csplashscreen.core.ui.paging.PagingSource
import st.slex.csplashscreen.feature.home.domain.HomeInteractor
import st.slex.csplashscreen.feature.home.navigation.HomeRouter
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore.Action
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore.Event
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore.Navigation
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactor: HomeInteractor,
    appDispatcher: AppDispatcher,
    router: HomeRouter
) : BaseViewModel<State, Event, Action, Navigation>(router, appDispatcher) {

    override val initialState: State = State(
        collections = ::collections,
        photos = ::photos
    )

    override val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    private val collections: StateFlow<PagingData<CollectionModel>>
        get() = Pager(config = config) { PagingSource(interactor::getAllCollections) }
            .state { collection -> collection.toPresentation() }

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = Pager(config = config) { PagingSource(interactor::getAllPhotos) }
            .state { image -> image.toPresentation() }

    override fun sendAction(action: Action) {
        when (action) {
            is Action.OnCollectionClick -> actionCollectionClick(action)
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
        }
    }

    private fun actionCollectionClick(action: Action.OnCollectionClick) {
        navigate(Navigation.Collection(action.uuid))
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        navigate(Navigation.Image(action.uuid))
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        navigate(Navigation.User(action.username))
    }

    companion object {

        private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}