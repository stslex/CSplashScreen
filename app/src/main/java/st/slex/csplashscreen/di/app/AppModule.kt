package st.slex.csplashscreen.di.app

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.core.coroutine.AppDispatcherImpl
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindsAppDispatcher(impl: AppDispatcherImpl): AppDispatcher
}