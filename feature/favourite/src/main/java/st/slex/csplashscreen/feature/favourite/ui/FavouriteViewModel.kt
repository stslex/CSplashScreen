package st.slex.csplashscreen.feature.favourite.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.State
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    store: FavouriteStore
) : BaseViewModel<State, Event, Action>(store)