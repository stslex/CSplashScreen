package st.slex.csplashscreen.feature.search.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.StoreComponent
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

interface SearchStoreComponent {

    @Stable
    data class State(
        val query: String,
        val historyItems: PagingData<SearchItem>,
        val searchItems: PagingData<PhotoModel>
    ) : StoreComponent.State {
        companion object {
            val INITIAL = State(
                query = "",
                historyItems = PagingData.empty(),
                searchItems = PagingData.empty()
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
        data class Init(val screen: Screen.SearchPhotosScreen) : Action

        @Stable
        data object ClearHistory : Action

        @Stable
        data class OnImageClick(val uuid: String) : Action

        @Stable
        data class OnProfileClick(val username: String) : Action

        @Stable
        data class OnQueryInput(val query: String) : Action

        @Stable
        data class OnSearchHistoryClick(val query: String) : Action
    }
}
