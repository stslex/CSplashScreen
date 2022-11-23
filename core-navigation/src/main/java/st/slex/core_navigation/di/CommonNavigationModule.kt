package st.slex.core_navigation.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.core_navigation.routers.CommonRouter
import st.slex.core_navigation.routers.CommonRouterImpl
import st.slex.core_navigation.routers.ImageRouter
import st.slex.core_navigation.routers.ImageRouterImpl
import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.AppNavigatorImpl

class CommonNavigationModule : AppModule {

    override fun invoke() = module {
        singleOf(::CommonRouterImpl) { bind<CommonRouter>() }
        singleOf(::ImageRouterImpl) { bind<ImageRouter>() }
        singleOf(::AppNavigatorImpl) { bind<AppNavigator>() }
    }
}