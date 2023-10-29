package st.slex.csplashscreen.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.State

interface FavouriteStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val photos: () -> StateFlow<PagingData<PhotoModel>>
    ) : Store.State

    @Stable
    sealed interface Event : Store.Event

    @Stable
    sealed interface Navigation : Store.Navigation {

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
    sealed interface Action : Store.Action {

        data class OnUserClick(
            val username: String
        ) : Action

        data class OnImageClick(
            val uuid: String
        ) : Action

        data object GoToPhotosClick : Action
    }
}

