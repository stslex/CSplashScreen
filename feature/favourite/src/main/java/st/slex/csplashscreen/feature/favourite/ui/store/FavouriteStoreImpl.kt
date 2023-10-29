package st.slex.csplashscreen.feature.favourite.ui.store

import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouter
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Navigation
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.State
import javax.inject.Inject

class FavouriteStoreImpl @Inject constructor(
    private val interactor: FavouriteInteractor,
    router: FavouriteRouter
) : FavouriteStore, BaseStore<State, Event, Action, Navigation>(router) {

    override val initialState: State = State(
        photos = ::photos
    )

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = interactor.photos
            .map { pagingData ->
                pagingData
            }
            .state()

    override fun processAction(action: Action) {
        when (action) {
            is Action.GoToPhotosClick -> actionGoHome()
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
        }
    }

    private fun actionGoHome() {
        navigate(Navigation.Home)
    }

    private fun actionImageClick(action: Action.OnImageClick) {
        navigate(Navigation.Image(action.uuid))
    }

    private fun actionUserClick(action: Action.OnUserClick) {
        navigate(Navigation.User(action.username))
    }
}