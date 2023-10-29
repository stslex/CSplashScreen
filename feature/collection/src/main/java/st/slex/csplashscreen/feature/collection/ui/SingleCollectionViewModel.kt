package st.slex.csplashscreen.feature.collection.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.State
import javax.inject.Inject

class SingleCollectionViewModel @Inject constructor(
    store: SingleCollectionStore,
) : BaseViewModel<State, Event, Action>(store)