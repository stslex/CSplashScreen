package st.slex.csplashscreen.feature.feature_photo_detail.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event.Navigation
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    router: ImageDetailRouter,
    store: ImageDetailStore
) : BaseViewModel<State, Event, Action, Navigation>(store, router)