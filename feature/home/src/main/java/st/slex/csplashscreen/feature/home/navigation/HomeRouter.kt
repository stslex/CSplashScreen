package st.slex.csplashscreen.feature.home.navigation

interface HomeRouter {

    fun navToProfile(username: String)

    fun navToImage(uuid: String)

    fun navToCollection(uuid: String)
}

