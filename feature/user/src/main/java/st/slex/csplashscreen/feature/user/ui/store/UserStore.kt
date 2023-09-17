package st.slex.csplashscreen.feature.user.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.network.model.ui.user.UserModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Action
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Event
import st.slex.csplashscreen.feature.user.ui.store.UserStore.State

interface UserStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val user: UserModel?,
        val photos: (String) -> StateFlow<PagingData<PhotoModel>>,
        val likes: (String) -> StateFlow<PagingData<PhotoModel>>,
        val collections: (String) -> StateFlow<PagingData<CollectionModel>>
    ) : Store.State

    @Stable
    sealed interface Event : Store.Event {

        sealed interface Navigation : Event {

            data object PopBack : Navigation

            data class User(
                val username: String
            ) : Navigation

            data class Image(
                val uuid: String
            ) : Navigation

            data class Collection(
                val uuid: String
            ) : Navigation
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        data class Init(
            val args: AppArguments.UserScreen
        ) : Action

        data object OnBackButtonClick : Action

        data class OnUserClick(
            val username: String
        ) : Action

        data class OnImageClick(
            val uuid: String
        ) : Action

        data class OnCollectionClick(
            val uuid: String
        ) : Action
    }
}

