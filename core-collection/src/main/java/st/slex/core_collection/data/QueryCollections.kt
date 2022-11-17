package st.slex.core_collection.data

sealed class QueryCollections {
    class UserCollections(val query: String) : QueryCollections()
    object AllCollections : QueryCollections()
    object EmptyQuery : QueryCollections()
}
