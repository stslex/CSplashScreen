package st.slex.feature_user.ui

import androidx.paging.compose.LazyPagingItems
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel

sealed class UserPagerTabResource<T : Any>(val pagingItems: LazyPagingItems<T>) {

    abstract val title: String

    data class Photos(
        private val items: LazyPagingItems<ImageModel>
    ) : UserPagerTabResource<ImageModel>(items) {
        override val title: String = "Photos"
    }

    data class Likes(
        private val items: LazyPagingItems<ImageModel>
    ) : UserPagerTabResource<ImageModel>(items) {
        override val title: String = "Likes"
    }

    data class Collections(
        private val items: LazyPagingItems<CollectionModel>
    ) : UserPagerTabResource<CollectionModel>(items) {
        override val title: String = "Collections"
    }
}