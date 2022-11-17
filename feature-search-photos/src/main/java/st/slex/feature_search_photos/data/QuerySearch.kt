package st.slex.feature_search_photos.data

sealed class QuerySearch {
    class SearchPhotos(val text: String) : QuerySearch()
    object EmptyQuery : QuerySearch()
}