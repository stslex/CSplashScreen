package st.slex.csplashscreen.feature.collection.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Action
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.Event
import st.slex.csplashscreen.feature.collection.ui.store.SingleCollectionStore.State

interface SingleCollectionStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val photos: () -> StateFlow<PagingData<PhotoModel>>
    ) : Store.State

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        sealed interface Navigation : Event {

            @Stable
            data class Profile(
                val username: String
            ) : Navigation

            @Stable
            data class ImageDetail(
                val uuid: String
            ) : Navigation
        }
    }

    @Stable
    sealed interface Action : Store.Action {

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