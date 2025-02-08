package st.slex.csplashscreen.feature.photo_detail.ui.presenter

import androidx.compose.runtime.Stable
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.ui.mvi.StoreComponent
import st.slex.csplashscreen.feature.photo_detail.domain.model.ImageDetail

interface ImageDetailStoreComponent : StoreComponent {

    @Stable
    data class State(
        val imageId: String,
        val screenState: ScreenState
    ) : StoreComponent.State {

        companion object {
            val INITIAL: State = State(
                imageId = "",
                screenState = ScreenState.Loading
            )
        }
    }

    @Stable
    sealed interface ScreenState {

        @Stable
        data object Loading : ScreenState

        @Stable
        data class Content(
            val imageDetail: ImageDetail
        ) : ScreenState

        @Stable
        data class Error(
            val throwable: Throwable
        ) : ScreenState

        val data: ImageDetail?
            get() = (this as? Content)?.imageDetail
    }

    @Stable
    sealed interface Event : StoreComponent.Event {

        @Stable
        data class Dialog(
            val type: DialogType
        ) : Event
    }

    enum class DialogType {
        DOWNLOAD,
        NONE
    }

    @Stable
    sealed interface Navigation : StoreComponent.Navigation {

        @Stable
        data class Profile(
            val username: String
        ) : Navigation

        @Stable
        data class Search(
            val tag: String
        ) : Navigation
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        @Stable
        data class Init(val screen: Screen.ImageDetailScreen) : Action

        @Stable
        data class SetWallpaperClick(val url: String) : Action

        @Stable
        data class OnTagClick(val tag: String) : Action

        @Stable
        data class OnProfileClick(val username: String) : Action

        @Stable
        data class OnLikeClicked(val imageDetail: ImageDetail) : Action

        @Stable
        data object DownloadImageButtonClick : Action

        @Stable
        data class DownloadImageChooseClick(val type: DownloadImageType) : Action

        @Stable
        data object CloseDialog : Action
    }
}
