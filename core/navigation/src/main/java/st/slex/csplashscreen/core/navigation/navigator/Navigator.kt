package st.slex.csplashscreen.core.navigation.navigator

interface Navigator {

    operator fun invoke(target: NavigationTarget)
}
