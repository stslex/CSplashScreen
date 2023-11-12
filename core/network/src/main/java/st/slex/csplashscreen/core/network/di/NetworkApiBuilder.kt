package st.slex.csplashscreen.core.network.di

import st.slex.csplashscreen.core.core.api.AppApi

object NetworkApiBuilder {

    fun build(
        appApi: AppApi
    ): NetworkClientApi = DaggerNetworkClientComponent
        .factory()
        .create(
            dependencies = DaggerNetworkDependenciesComponent
                .factory()
                .create(appApi)
        )
}