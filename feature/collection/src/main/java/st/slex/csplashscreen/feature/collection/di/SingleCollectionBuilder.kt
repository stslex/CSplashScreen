package st.slex.csplashscreen.feature.collection.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import st.slex.csplashscreen.feature.collection.ui.SingleCollectionViewModel

object SingleCollectionBuilder {

    fun build() {

    }
}

@Composable
fun setupComponent(key: String): SingleCollectionViewModel {
    return viewModel<SingleCollectionViewModel>()
}
