package st.slex.core_photos.data

sealed class QueryPhotos {
    data class CollectionPhotos(val query: String) : QueryPhotos()
    data class UserPhotos(val username: String) : QueryPhotos()
    data class UserLikes(val username: String) : QueryPhotos()
    data class TopicPhotos(val id: String) : QueryPhotos()
    object AllPhotos : QueryPhotos()
    object EmptyQuery : QueryPhotos()
}