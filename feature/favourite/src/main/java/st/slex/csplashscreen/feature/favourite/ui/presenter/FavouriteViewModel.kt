package st.slex.csplashscreen.feature.favourite.ui.presenter

import kotlinx.coroutines.flow.map
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
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
) : BaseViewModel<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {

    override fun sendAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit()
            is Action.GoToPhotosClick -> actionGoHome()
            is Action.OnImageClick -> actionImageClick(action)
            is Action.OnUserClick -> actionUserClick(action)
        }
    }

    private fun actionInit() {
        interactor.photos
            .map { pagingData ->
                pagingData
            }
            .state()
            .launch { data ->
                updateState { state ->
                    state.copy(photos = data)
                }
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