package st.slex.csplashscreen.data.photos

sealed class QueryPhotos {
    class CollectionPhotos(val query: String) : QueryPhotos()
    object AllPhotos : QueryPhotos()
    object EmptyQuery : QueryPhotos()
}