package st.slex.csplashscreen.feature.feature_photo_detail.ui.store

import kotlinx.coroutines.flow.MutableStateFlow
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Navigation
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.ScreenState
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import javax.inject.Inject

class ImageDetailStoreImpl @Inject constructor(
    private val interactor: ImageDetailInteractor,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    appDispatcher: AppDispatcher,
    router: ImageDetailRouter
) : ImageDetailStore, BaseStore<State, Event, Action, Navigation>(router, appDispatcher) {

    override val initialState: State = State(
        imageId = "",
        screenState = ScreenState.Loading
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.DownloadImageClick -> actionDownloadImageClick(action)
            is Action.OnLikeClicked -> actionLikeClick(action)
            is Action.OnProfileClick -> actionProfileClick(action)
            is Action.OnTagClick -> actionTagClick(action)
            is Action.SetWallpaperClick -> setWallpaperClick(action)
        }
    }

    private fun actionDownloadImageClick(action: Action.DownloadImageClick) {
        downloadImageUseCase(
            url = action.url,
            fileName = state.value.imageId
        )
    }

    private fun actionLikeClick(action: Action.OnLikeClicked) {
        launch {
            runCatching {
                interactor.like(action.imageDetail.photo)
            }.onFailure { error ->
                Logger.exception(error)
            }
        }
    }

    private fun actionProfileClick(action: Action.OnProfileClick) {
        navigate(Navigation.Profile(action.username))
    }

    private fun actionTagClick(action: Action.OnTagClick) {
        navigate(Navigation.Search(action.tag))
    }

    private fun setWallpaperClick(action: Action.SetWallpaperClick) {
        wallpaperSetUseCase(action.url)
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(imageId = action.args.imageId)
        }
        interactor.getImageDetail(action.args.imageId)
            .launch(
                catch = { error ->
                    updateState { currentState ->
                        currentState.copy(
                            screenState = ScreenState.Error(error)
                        )
                    }
                },
                each = { imageDetail ->
                    updateState { currentState ->
                        currentState.copy(
                            screenState = ScreenState.Content(imageDetail)
                        )
                    }
                }
            )
    }
}