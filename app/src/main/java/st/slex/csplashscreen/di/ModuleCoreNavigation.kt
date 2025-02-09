package st.slex.csplashscreen.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorImpl
import st.slex.csplashscreen.core.navigation.navigator.holder.NavigatorHolder
import st.slex.csplashscreen.navigation.BaseNavigationHolder
import st.slex.csplashscreen.navigation.NavigatorHolderImpl

val moduleCoreNavigation = module {
    val navigatorHolder = NavigatorHolderImpl()
    single<NavigatorHolder> { navigatorHolder }
    single<BaseNavigationHolder> { navigatorHolder }

    singleOf(::NavigatorImpl) { bind<Navigator>() }
}