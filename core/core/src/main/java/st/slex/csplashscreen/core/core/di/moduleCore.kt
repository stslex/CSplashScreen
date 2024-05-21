package st.slex.csplashscreen.core.core.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.core.coroutine.AppDispatcherImpl

val moduleCore = module {
    singleOf(::AppDispatcherImpl) { bind<AppDispatcher>() }
}