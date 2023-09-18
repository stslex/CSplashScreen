package st.slex.csplashscreen.feature.user.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.user.navigation.UserRouter
import st.slex.csplashscreen.feature.user.ui.store.UserStore
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Event
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Event.Navigation
import st.slex.csplashscreen.feature.user.ui.store.UserStore.State
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val router: UserRouter,
    store: UserStore
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Navigation) {
        when (event) {
            is Navigation.Collection -> router.navToCollection(event.uuid)
            is Navigation.Image -> router.navToImage(event.uuid)
            is Navigation.PopBack -> router.popBack()
            is Navigation.User -> router.navToUser(event.username)
        }
    }
}