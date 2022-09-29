package st.slex.csplashscreen.ui.screens.user

import androidx.paging.compose.LazyPagingItems
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel

sealed class UserPagerTabResource<T : Any>(val pagingItems: LazyPagingItems<T>) {

    abstract val title: String

    data class Photos(val items: LazyPagingItems<ImageModel>) :
        UserPagerTabResource<ImageModel>(items) {
        override val title: String = "Photos"
    }

    data class Likes(val items: LazyPagingItems<ImageModel>) :
        UserPagerTabResource<ImageModel>(items) {
        override val title: String = "Likes"
    }

    data class Collections(val items: LazyPagingItems<CollectionModel>) :
        UserPagerTabResource<CollectionModel>(items) {
        override val title: String = "Collections"
    }
}