package st.slex.feature_main.data

sealed class QueryCollections {
    class UserCollections(val query: String) : QueryCollections()
    object AllCollections : QueryCollections()
    object EmptyQuery : QueryCollections()
}
