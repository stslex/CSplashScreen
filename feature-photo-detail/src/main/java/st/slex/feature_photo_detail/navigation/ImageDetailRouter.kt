package st.slex.feature_photo_detail.navigation

import st.slex.core_navigation.routers.CommonRouter

interface ImageDetailRouter : CommonRouter {

    fun onTagClick(tag: String)

    fun navToRawImage(url: String)
}