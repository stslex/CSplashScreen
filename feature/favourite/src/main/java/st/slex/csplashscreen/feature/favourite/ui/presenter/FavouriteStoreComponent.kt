package st.slex.csplashscreen.feature.favourite.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.StoreComponent

interface FavouriteStoreComponent : StoreComponent {

    @Stable
    data class State(
        val photos: PagingData<PhotoModel>
    ) : StoreComponent.State {

        companion object {
            val INITIAL = State(
                photos = PagingData.empty()
            )
        }
    }

    @Stable
    sealed interface Event : StoreComponent.Event

    @Stable
    sealed interface Navigation : StoreComponent.Navigation {

        @Stable
        data class User(
            val username: String
        ) : Navigation

        @Stable
        data class Image(
            val uuid: String
        ) : Navigation

        @Stable
        data object Home : Navigation
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        data object Init : Action

        data class OnUserClick(
            val username: String
        ) : Action

        data class OnImageClick(
            val uuid: String
        ) : Action

        data object GoToPhotosClick : Action
    }
}

