package st.slex.csplashscreen.core.database.di

import st.slex.csplashscreen.core.core.AppApi

object DatabaseApiBuilder {

    fun build(appApi: AppApi): DatabaseApi = DaggerDatabaseComponent
        .factory()
        .create(
            dependencies = DaggerDatabaseDependenciesComponent
                .factory()
                .create(appApi)
        )
}