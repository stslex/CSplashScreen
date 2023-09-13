package st.slex.csplashscreen.feature.feature_photo_detail.ui.store

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import st.slex.csplashscreen.core.ui.mvi.BaseStoreImpl
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
) : ImageDetailStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        imageId = "",
        screenState = ScreenState.Loading
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    override fun processAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.DownloadImageClick -> TODO()
            is Action.OnLikeClicked -> TODO()
            is Action.OnProfileClick -> TODO()
            is Action.OnTagClick -> TODO()
            is Action.SetWallpaperClick -> TODO()
        }
    }

    fun actionInit(action: Action.Init) {
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