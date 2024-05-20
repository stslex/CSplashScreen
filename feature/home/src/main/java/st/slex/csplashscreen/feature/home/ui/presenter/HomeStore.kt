package st.slex.csplashscreen.feature.home.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import st.slex.csplashscreen.core.collection.ui.model.CollectionModel
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store

interface HomeStore : Store {

    @Stable
    data class State(
        val collections: PagingData<CollectionModel>,
        val photos: PagingData<PhotoModel>
    ) : Store.State {

        companion object {
            val INIT = State(
                collections = PagingData.empty(),
                photos = PagingData.empty()
            )
        }
    }

    @Stable
    sealed interface Event : Store.Event

    @Stable
    sealed interface Navigation : Store.Navigation {

        @Stable
        data class User(
            val username: String
        ) : Navigation

        @Stable
        data class Collection(
            val uuid: String
        ) : Navigation

        @Stable
        data class Image(
            val uuid: String
        ) : Navigation
    }

    @Stable
    sealed interface Action : Store.Action {

        data object Init : Action

        @Stable
        data class OnUserClick(
            val username: String
        ) : Action

        @Stable
        data class OnCollectionClick(
            val uuid: String
        ) : Action

        @Stable
        data class OnImageClick(
            val uuid: String
        ) : Action
    }
}

