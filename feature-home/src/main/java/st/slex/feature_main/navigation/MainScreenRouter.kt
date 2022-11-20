package st.slex.feature_main.navigation

interface MainScreenRouter {

    fun navToProfile(username: String)

    fun navToDetailImage(url: String, imageId: String)

    fun navToSingleCollection(id: String)
}