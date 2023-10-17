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
    router: UserRouter,
    store: UserStore
) : BaseViewModel<State, Event, Action, Navigation>(store, router)