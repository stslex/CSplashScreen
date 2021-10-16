package st.slex.csplashscreen.data.core

sealed class QueryCollections {
    class UserCollections(val query: String) : QueryCollections()
    object AllCollections : QueryCollections()
    object EmptyQuery : QueryCollections()
}
