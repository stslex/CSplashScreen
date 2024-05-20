package st.slex.csplashscreen.feature.user.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.network.model.ui.user.UserModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store

interface UserStore {

    @Stable
    data class State(
        val user: UserModel?,
        val photos: PagingData<PhotoModel>,
        val likes: PagingData<PhotoModel>,
        val collections: PagingData<CollectionModel>
    ) : Store.State {
        companion object {
            val INITIAL = State(
                user = null,
                photos = PagingData.empty(),
                likes = PagingData.empty(),
                collections = PagingData.empty()
            )
        }
    }

    @Stable
    sealed interface Event : Store.Event

    sealed interface Navigation : Store.Navigation {

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

