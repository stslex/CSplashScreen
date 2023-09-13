package st.slex.csplashscreen.feature.favourite.navigation

interface FavouriteRouter {

    fun navToUser(username: String)

    fun navToImage(uuid: String)

    fun navHome()
}

