package st.slex.csplashscreen.feature.collection.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.StoreComponent

interface SingleCollectionStoreComponent {

    @Stable
    data class State(
        val photos: PagingData<PhotoModel>,
        val collectionId: String,
    ) : StoreComponent.State {
        companion object {
            val INITIAL = State(
                photos = PagingData.empty(),
                collectionId = ""
            )
        }
    }

    @Stable
    sealed interface Event : StoreComponent.Event

    @Stable
    sealed interface Navigation : StoreComponent.Navigation {

        @Stable
        data class Profile(
            val username: String
        ) : Navigation

        @Stable
        data class ImageDetail(
            val uuid: String
        ) : Navigation
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        @Stable
        data class Init(
            val collectionId: String
        ) : Action

        @Stable
        data class OnImageClick(
            val uuid: String
        ) : Action

        @Stable
        data class OnProfileClick(
            val username: String
        ) : Action
    }
}