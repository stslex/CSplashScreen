package st.slex.csplashscreen.data.search

sealed class QuerySearch {
    class SearchPhotos(val text: String) : QuerySearch()
    object EmptyQuery : QuerySearch()
}