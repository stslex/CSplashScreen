package st.slex.csplashscreen.feature.favourite.ui.store

import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.State
import javax.inject.Inject

class FavouriteStoreImpl @Inject constructor(
    private val interactor: FavouriteInteractor
) : FavouriteStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        photos = ::photos
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = interactor.photos
            .map { pagingData ->
                pagingData
            }
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    override fun processAction(action: Action) {
        when (action) {
            is Action.GoToPhotosClick -> actionGoHome()
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
        }
    }

    private fun actionGoHome() {
        sendEvent(Event.Navigation.Home)
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        sendEvent(Event.Navigation.Image(action.uuid))
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        sendEvent(Event.Navigation.User(action.username))
    }
}