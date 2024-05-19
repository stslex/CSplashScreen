package st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter

import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.ui.mvi.BaseViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.DownloadImageType.LARGE
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.DownloadImageType.MEDIUM
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.DownloadImageType.ORIGINAL
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.DownloadImageType.SMALL
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.DialogType
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Navigation
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.ScreenState
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.State
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val interactor: ImageDetailInteractor,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    appDispatcher: AppDispatcher,
    router: ImageDetailRouter
) : BaseViewModel<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL
) {

    override fun sendAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.DownloadImageButtonClick -> actionDownloadImageButtonClick()
            is Action.OnLikeClicked -> actionLikeClick(action)
            is Action.OnProfileClick -> actionProfileClick(action)
            is Action.OnTagClick -> actionTagClick(action)
            is Action.SetWallpaperClick -> setWallpaperClick(action)
            is Action.DownloadImageChooseClick -> actionDownloadImageChooseClick(action)
            is Action.CloseDialog -> actionCloseDialog()
        }
    }

    private fun actionCloseDialog() {
        sendEvent(Event.Dialog(DialogType.NONE))
    }

    private fun actionDownloadImageButtonClick() {
        // TODO Show dialog to choose type
        sendEvent(Event.Dialog(DialogType.DOWNLOAD))
    }

    private fun actionDownloadImageChooseClick(
        action: Action.DownloadImageChooseClick
    ) {
        val photo = state.value.screenState.data?.photo ?: return // TODO show Toast
        launchCatching(
            block = {
                when (action.type) {
                    LARGE -> photo.urls.raw
                    MEDIUM -> photo.urls.regular
                    SMALL -> photo.urls.small
                    ORIGINAL -> interactor.getDownloadLink(state.value.imageId)
                }
            }
        ) { downloadUrl ->
            downloadImageUseCase(
                url = downloadUrl,
                fileName = state.value.imageId
            )
            sendEvent(Event.Dialog(DialogType.NONE))
        }
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
                onError = { error ->
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