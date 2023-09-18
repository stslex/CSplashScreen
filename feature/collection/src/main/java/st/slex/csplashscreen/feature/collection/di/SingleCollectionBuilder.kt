package st.slex.csplashscreen.feature.collection.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.collection.ui.SingleCollectionViewModel

object SingleCollectionBuilder {

    fun build(
        navigationApi: NavigationApi
    ): SingleCollectionComponent = DaggerSingleCollectionComponent
        .factory()
        .create(
            dependencies = DaggerSingleCollectionComponent_SingleCollectionDependenciesComponent
                .factory()
                .create(
                    networkClientApi = NetworkApiBuilder.build(),
                    navigationApi = navigationApi
                )

        )
}

@Composable
fun setupComponent(key: String): SingleCollectionViewModel {
    val context = LocalContext.current
    return daggerViewModel(key) {
        SingleCollectionBuilder
            .build(context.navigationApi)
            .viewModelFactory
    }
}
