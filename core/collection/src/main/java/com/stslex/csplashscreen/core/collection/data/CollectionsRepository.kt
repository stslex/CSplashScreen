package com.stslex.csplashscreen.core.collection.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.CollectionModel

interface CollectionsRepository {
    fun queryAll(query: QueryCollections): PagingSource<Int, CollectionModel>
}