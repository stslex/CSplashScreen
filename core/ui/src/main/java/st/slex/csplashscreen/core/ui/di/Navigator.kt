package st.slex.csplashscreen.core.ui.di

import androidx.navigation.NavHostController

interface Navigator {

    val controller: NavHostController

    operator fun invoke(screen: Screen)
}
