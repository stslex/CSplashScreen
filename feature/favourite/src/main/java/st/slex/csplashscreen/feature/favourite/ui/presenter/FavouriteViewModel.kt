package st.slex.csplashscreen.feature.favourite.ui.presenter

import androidx.paging.PagingData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.BaseViewModel
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouter
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.Navigation
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.State
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val interactor: FavouriteInteractor,
    appDispatcher: AppDispatcher,
    router: FavouriteRouter
) : BaseViewModel<State, Event, Action, Navigation>(router, appDispatcher) {

    override val initialState: State = State(
        photos = ::photos
    )

    override val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    private val photos: StateFlow<PagingData<PhotoModel>>
        get() = interactor.photos
            .map { pagingData ->
                pagingData
            }
            .state()

    override fun sendAction(action: Action) {
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