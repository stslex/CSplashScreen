package st.slex.csplashscreen.feature.user.navigation

interface UserRouter {

    fun popBack()

    fun navToUser(username: String)

    fun navToImage(uuid: String)

    fun navToCollection(uuid: String)
}

