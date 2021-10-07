package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NavOptions

interface NavigationAction {
    val destination: String
        get() = ""
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
}
