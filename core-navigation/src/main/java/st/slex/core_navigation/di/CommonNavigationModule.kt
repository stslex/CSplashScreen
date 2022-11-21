package st.slex.core_navigation.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.core_navigation.routers.CommonRouter
import st.slex.core_navigation.routers.CommonRouterImpl
import st.slex.core_navigation.routers.ImageRouter
import st.slex.core_navigation.routers.ImageRouterImpl

class CommonNavigationModule : AppModule {
    override val module = module {
        singleOf(::CommonRouterImpl) { bind<CommonRouter>() }
        singleOf(::ImageRouterImpl) { bind<ImageRouter>() }
    }
}