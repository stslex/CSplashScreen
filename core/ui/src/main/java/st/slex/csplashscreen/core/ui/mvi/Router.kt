package st.slex.csplashscreen.core.ui.mvi

fun interface Router<in E : Store.Navigation> {
    operator fun invoke(event: E)
}