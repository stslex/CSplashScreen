package st.slex.csplashscreen.data.photos

sealed class QueryPhotos {
    class CollectionPhotos(val query: String) : QueryPhotos()
    class UserPhotos(val username: String) : QueryPhotos()
    class UserLikes(val username: String) : QueryPhotos()
    class TopicPhotos(val id: String) : QueryPhotos()
    object AllPhotos : QueryPhotos()
    object EmptyQuery : QueryPhotos()
}