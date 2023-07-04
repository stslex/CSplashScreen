package st.slex.feature_main.ui

import androidx.compose.runtime.Stable
import androidx.paging.compose.LazyPagingItems
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.UIItemTypes
import st.slex.feature_home.R

@Stable
sealed class MainPagerTabResource<T : UIItemTypes>(
    val pagingItems: LazyPagingItems<T>
) {

    abstract val title: Int

    @Stable
    data class Photos(
        val items: LazyPagingItems<ImageModel>
    ) : MainPagerTabResource<ImageModel>(items) {
        override val title: Int = R.string.main_screen_tab_photos
    }

    @Stable
    data class Collections(
        val items: LazyPagingItems<CollectionModel>
    ) : MainPagerTabResource<CollectionModel>(items) {
        override val title: Int = R.string.main_screen_tab_collections
    }
}