package st.slex.csplashscreen.ui.screens.main

sealed interface PagerMainTab {

    fun getTitle(): String

    class Photos(val data: String = "Photos") : PagerMainTab {
        override fun getTitle(): String = data
    }

    class Collections(val data: String = "Collections") : PagerMainTab {
        override fun getTitle(): String = data
    }
}