package st.slex.feature_user.navigation

import st.slex.core_navigation.routers.ImageRouter

interface UserRouter : ImageRouter {
    fun navToCollection(id: String)
}