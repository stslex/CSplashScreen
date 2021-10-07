package st.slex.csplashscreen.ui.screens.main

sealed interface PagerMainTab {

    val title: String

    object Photos : PagerMainTab {
        override val title: String = "Photos"
    }

    object Collections : PagerMainTab {
        override val title: String = "Collections"
    }
}