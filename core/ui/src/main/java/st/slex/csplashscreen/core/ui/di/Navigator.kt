package st.slex.csplashscreen.core.ui.di

fun interface Navigator {
    operator fun invoke(screen: Screen)
}
