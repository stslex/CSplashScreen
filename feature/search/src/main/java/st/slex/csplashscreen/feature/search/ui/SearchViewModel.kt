package st.slex.csplashscreen.feature.search.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouter
import st.slex.csplashscreen.feature.search.ui.store.SearchStore
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Action
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Event
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.State
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    store: SearchStore,
    private val router: SearchPhotosRouter
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            is Event.Navigation.ImageDetail -> router.navToImage(event.uuid)
            is Event.Navigation.Profile -> router.navToProfile(event.username)
        }
    }
}