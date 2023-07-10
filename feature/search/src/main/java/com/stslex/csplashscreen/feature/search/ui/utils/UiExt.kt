package com.stslex.csplashscreen.feature.search.ui.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

object UiExt {

    val CombinedLoadStates.isNotLoading: Boolean
        get() = this.append is LoadState.NotLoading &&
                this.prepend is LoadState.NotLoading &&
                this.refresh is LoadState.NotLoading
}