package st.slex.csplashscreen.feature.home.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.home.ui.store.HomeStore
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Action
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Event
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    store: HomeStore
) : BaseViewModel<State, Event, Action>(store)