package st.slex.feature_main.navigation

import st.slex.core_navigation.routers.ImageRouter

interface MainScreenRouter : ImageRouter {
    fun navToSingleCollection(id: String)
}