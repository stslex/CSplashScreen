package st.slex.csplashscreen.feature.feature_photo_detail.ui.store

import androidx.compose.runtime.Stable
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State

interface ImageDetailStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val imageId: String,
        val screenState: ScreenState
    ) : Store.State

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
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        sealed interface Navigation : Event {

            @Stable
            data class Profile(
                val username: String
            ) : Navigation

            @Stable
            data class Search(
                val tag: String
            ) : Navigation
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val args: AppArguments.ImageDetailScreen
        ) : Action

        @Stable
        data class DownloadImageClick(
            val url: String,
        ) : Action

        @Stable
        data class SetWallpaperClick(
            val url: String
        ) : Action

        @Stable
        data class OnTagClick(
            val tag: String
        ) : Action

        @Stable
        data class OnProfileClick(
            val username: String
        ) : Action

        @Stable
        data class OnLikeClicked(
            val imageDetail: ImageDetail
        ) : Action
    }
}

