package st.slex.csplashscreen.di

import st.slex.csplashscreen.ui.InitialAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InitialAppViewModel)
}