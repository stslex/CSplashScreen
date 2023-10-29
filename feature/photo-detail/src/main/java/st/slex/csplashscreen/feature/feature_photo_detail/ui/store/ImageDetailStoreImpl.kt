package st.slex.csplashscreen.feature.feature_photo_detail.ui.store

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.ui.mvi.BaseStore
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.ScreenState
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import javax.inject.Inject

class ImageDetailStoreImpl @Inject constructor(
    private val interactor: ImageDetailInteractor,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
) : ImageDetailStore, BaseStore<State, Event, Action>() {

    override val initialState: State = State(
        imageId = "",
        screenState = ScreenState.Loading
    )

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
        scope.launch(Dispatchers.IO) {
            runCatching {
                interactor.like(action.imageDetail.photo)
            }.onFailure { error ->
                Logger.exception(error)
            }
        }
    }

    private fun actionProfileClick(action: Action.OnProfileClick) {
        sendEvent(Event.Navigation.Profile(action.username))
    }

    private fun actionTagClick(action: Action.OnTagClick) {
        sendEvent(Event.Navigation.Search(action.tag))
    }

    private fun setWallpaperClick(action: Action.SetWallpaperClick) {
        wallpaperSetUseCase(action.url)
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(imageId = action.args.imageId)
        }
        interactor.getImageDetail(action.args.imageId)
            .flowOn(Dispatchers.IO)
            .catch { error ->
                updateState { currentState ->
                    currentState.copy(
                        screenState = ScreenState.Error(error)
                    )
                }
            }
            .onEach { imageDetail ->
                updateState { currentState ->
                    currentState.copy(
                        screenState = ScreenState.Content(imageDetail)
                    )
                }
            }
            .launchIn(scope)
    }
}