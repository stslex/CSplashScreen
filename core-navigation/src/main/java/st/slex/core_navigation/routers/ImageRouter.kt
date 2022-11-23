package st.slex.core_navigation.routers

interface ImageRouter : CommonRouter {
    fun navToDetailImage(url: String, imageId: String)
}