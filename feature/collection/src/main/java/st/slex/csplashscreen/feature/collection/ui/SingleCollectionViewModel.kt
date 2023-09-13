package st.slex.csplashscreen.feature.collection.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.collection.navigation.SingleCollectionRouter
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event.Navigation
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.State
import javax.inject.Inject

class SingleCollectionViewModel @Inject constructor(
    store: SingleCollectionStore,
    private val router: SingleCollectionRouter
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Navigation) {
        when (event) {
            is Navigation.ImageDetail -> router.navToImage(event.uuid)
            is Navigation.Profile -> router.navToProfile(event.username)
        }
    }
}