package st.slex.csplashscreen.ui.screens.main

import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel


sealed class MainPagerTabResource<T : Any>(val pagingItems: LazyPagingItems<T>) {

    abstract val title: String

    data class Photos(val items: LazyPagingItems<ImageModel>) :
        MainPagerTabResource<ImageModel>(items) {
        override val title: String = "Photos"
    }

    data class Collections(val items: LazyPagingItems<CollectionModel>) :
        MainPagerTabResource<CollectionModel>(items) {
        override val title: String = "Collections"
    }
}