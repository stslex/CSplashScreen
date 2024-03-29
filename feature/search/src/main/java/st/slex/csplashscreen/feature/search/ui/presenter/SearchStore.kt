package st.slex.csplashscreen.feature.search.ui.presenter

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import kotlinx.coroutines.flow.StateFlow
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.mvi.Store
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

interface SearchStore {

    @Stable
    data class State(
        val query: String,
        val historyItems: () -> StateFlow<PagingData<SearchItem>>,
        val searchItems: () -> StateFlow<PagingData<PhotoModel>>
    ) : Store.State

    @Stable
    sealed interface Event : Store.Event

    @Stable
    sealed interface Navigation : Store.Navigation {

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
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val args: AppArguments.SearchPhotosScreen
        ) : Action

        @Stable
        data object ClearHistory : Action

        @Stable
        data class OnImageClick(
            val uuid: String
        ) : Action

        @Stable
        data class OnProfileClick(
            val username: String
        ) : Action

        @Stable
        data class OnQueryInput(
            val query: String
        ) : Action

        @Stable
        data class OnSearchHistoryClick(
            val query: String
        ) : Action
    }
}
